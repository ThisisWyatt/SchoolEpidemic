package com.smart.go.service.impl;

import com.smart.go.domain.MoveInfo;
import com.smart.go.service.CountPeopleService;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
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

    @Override
    public List<String> countInPeriod(String startTimeStr, String endTimeStr, String location) throws ParseException {

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date startTime = sdf1.parse(startTimeStr);
        Date endTime = sdf1.parse(endTimeStr);
        Date zeroTime = sdf2.parse(startTimeStr);//当天凌晨时间

        // description 查询在一段时间内某个建筑的所有新接入、断开、切换出、切换入的所有用户
        List<String> moveInfoList1 = moveInfoService.count1("%" + location + "%", startTime, endTime);
        System.out.println("列表1长度为：" + moveInfoList1.size());

        // description 查询在当天凌晨0点到某一时间点前全部连接过用户Id (add  location_to)
        List<String> peopleIdList = moveInfoService.count2("%" + location + "%", zeroTime, endTime);
        List<String> moveInfoList2 = new LinkedList<>();
        for (String peopleId : peopleIdList) {
            //如果用户距离这个时间点最近的AP行为为add、location_from则表示已经接入当前点
            MoveInfo m = moveInfoService.sPoint(peopleId, zeroTime, startTime);
            if (m != null) {
                if (m.getLocation() != null && m.getApType().equals("增加") && m.getLocation().startsWith(location))
                    moveInfoList2.add(m.getPeopleId());
                else if (m.getLocationTo() != null && m.getLocationTo().startsWith(location))
                    moveInfoList2.add(m.getPeopleId());
            }
        }
        System.out.println("列表2长度为：" + moveInfoList2.size());
        for (String m : moveInfoList2)
            System.out.println(m);
        System.out.println("-------------------------------------");


        //求并集
        moveInfoList1.removeAll(moveInfoList2);
        moveInfoList1.addAll(moveInfoList2);

        return moveInfoList1;
    }

    @Override
    public List<String> countAtPoint(String pointTime0, String location) throws ParseException {

        //当天凌晨时间
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        Date zeroTime = sdf1.parse(pointTime0);

        //目标时间点
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date pointTime = sdf2.parse(pointTime0);

        //查询当天凌晨0点到当天某一时间点前某个地点所有 接入、切换入的所有用户ID (add  location_to)
        List<String> peopleIdList0 = moveInfoService.count2("%" + location + "%", zeroTime, pointTime);
        List<String> moveInfoList1 = new LinkedList<>();
        System.out.println("peopleIdList.size()=" + peopleIdList0.size());
        for (String peopleId : peopleIdList0) {
            //如果用户距离这个时间点最近的AP行为为add、location_from则表示已经接入当前点
            MoveInfo m = moveInfoService.sPoint(peopleId, zeroTime, pointTime);
            if (m != null) {
                if (m.getLocation() != null && m.getApType().equals("增加") && m.getLocation().startsWith(location))
                    moveInfoList1.add(m.getPeopleId());
                else if (m.getLocationTo() != null && m.getLocationTo().startsWith(location))
                    moveInfoList1.add(m.getPeopleId());
            }
        }

        for (String m : moveInfoList1) {
            System.out.println(m);
        }


        return moveInfoList1;
    }


}
