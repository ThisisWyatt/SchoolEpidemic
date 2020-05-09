package com.smart.go.service.impl;

import com.smart.go.content.CountPeople;
import com.smart.go.dao.ApDao;
import com.smart.go.domain.MoveInfo;
import com.smart.go.service.CountPeopleService;
import com.smart.go.content.CountMessage;
import com.smart.go.util.ResultBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Description
 * Author cloudr
 * Date 2020/5/5 22:18
 * Version 1.0
 **/
@Service
public class CountPeopleServiceImpl implements CountPeopleService {

    @Resource
    private MoveInfoServiceImpl moveInfoService;
    @Resource
    private ApDao apDao;


    @Override
    // description 查询目标时间段内在该地点有操作的所有用户
    public ResultBean countInPeriod(CountMessage message) throws ParseException {

        ResultBean resultBean = new ResultBean();
        resultBean.setDataList(countInPeriodUtil(message));
        resultBean.setSuccess(true);
        resultBean.setMessage("查询成功");

        return resultBean;
    }

    List<String> countInPeriodUtil(CountMessage message) throws ParseException {

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date startTime = sdf1.parse(message.getStartTime());
        Date endTime = sdf1.parse(message.getEndTime());
        Date zeroTime = sdf2.parse(message.getStartTime());//当天凌晨时间

        // description 查询在一段时间内某个建筑的所有新接入、断开、切换出、切换入的所有用户
        List<String> moveInfoList1 = moveInfoService.count1("%" + message.getLocation() + "%", startTime, endTime);

        // description 查询在当天凌晨0点到某一时间点前全部连接过用户Id (add  location_to)
        List<String> peopleIdList = moveInfoService.count2("%" + message.getLocation() + "%", zeroTime, endTime);
        List<String> moveInfoList2 = new LinkedList<>();
        for (String peopleId : peopleIdList) {
            //如果用户距离这个时间点最近的AP行为为add、location_from则表示已经接入当前点
            MoveInfo m = moveInfoService.sPoint(peopleId, zeroTime, startTime);
            if (m != null) {
                if (m.getLocation() != null && m.getApType().equals("增加") && m.getLocation().startsWith(message.getLocation()))
                    moveInfoList2.add(m.getPeopleId());
                else if (m.getLocationTo() != null && m.getLocationTo().startsWith(message.getLocation()))
                    moveInfoList2.add(m.getPeopleId());
            }
        }

        //求并集
        moveInfoList1.removeAll(moveInfoList2);
        moveInfoList1.addAll(moveInfoList2);

        return moveInfoList1;
    }






    @Override
    // description 查询当前点接入的所有用户
    public ResultBean countAtPoint(CountMessage countMessage) throws ParseException {

        //当天凌晨时间
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        Date zeroTime = sdf1.parse(countMessage.getStartTime());

        //目标时间点
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date pointTime = sdf2.parse(countMessage.getStartTime());

        //查询当天凌晨0点到当天某一时间点前某个地点所有 接入、切换入的所有用户ID (add  location_to)
        List<String> peopleIdList0 = moveInfoService.count2("%" + countMessage.getLocation() + "%", zeroTime, pointTime);
        List<String> moveInfoList1 = new LinkedList<>();
        System.out.println("peopleIdList.size()=" + peopleIdList0.size());
        for (String peopleId : peopleIdList0) {
            //如果用户距离这个时间点最近的AP行为为add、location_from则表示已经接入当前点
            MoveInfo m = moveInfoService.sPoint(peopleId, zeroTime, pointTime);
            if (m != null) {
                if (m.getLocation() != null && m.getApType().equals("增加") && m.getLocation().startsWith(countMessage.getLocation()))
                    moveInfoList1.add(m.getPeopleId());
                else if (m.getLocationTo() != null && m.getLocationTo().startsWith(countMessage.getLocation()))
                    moveInfoList1.add(m.getPeopleId());
            }
        }

        for (String m : moveInfoList1) {
            System.out.println(m);
        }

        ResultBean resultBean = new ResultBean();
        resultBean.setDataList(moveInfoList1);
        resultBean.setMessage("查询成功");
        resultBean.setSuccess(true);

        return resultBean;
    }

    @Override
    // description  查询目标时间段所有建筑中Ap接入人数
    public ResultBean countInPeriodInAllBuildings(CountMessage message) throws ParseException {

        List<String> buildingList = apDao.getBuildingList();
        List<CountPeople> countPeopleList = new LinkedList<>();

        // 对每一栋建筑查询相应时段的人数
        for (String building : buildingList) {
            //构造查询条件
            CountMessage m = new CountMessage(message.getStartTime(), message.getEndTime(), building);
            int num = countInPeriod(m).getDataList().size();
            countPeopleList.add(new CountPeople(building, num));
        }

        ResultBean resultBean = new ResultBean();
        resultBean.setDataList(countPeopleList);
        resultBean.setSuccess(true);
        resultBean.setMessage("查询成功");

        return resultBean;
    }


}
