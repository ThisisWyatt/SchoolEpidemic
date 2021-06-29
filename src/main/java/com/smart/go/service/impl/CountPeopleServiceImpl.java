package com.smart.go.service.impl;

import com.smart.go.content.ApInfoProjection;
import com.smart.go.content.CountMessage;
import com.smart.go.content.CountPeopleMessage;
import com.smart.go.dao.ApDao;
import com.smart.go.domain.MoveInfo;
import com.smart.go.service.CountPeopleService;
import com.smart.go.util.GPSUtil;
import com.smart.go.util.ResultBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Description 各种人数统计的具体实现
 * Author wyatt
 * Date 2020/5/5 22:18
 * Version 1.0
 **/
@Service
public class CountPeopleServiceImpl implements CountPeopleService {

    @Resource
    private MoveInfoServiceImpl moveInfoService;
    @Resource
    private ApDao apDao;


    /**
     * 查询目标时间段内在该地点有操作的所有用户
     *
     * @param message 地点、目标时间段的开始和结束时间
     * @return
     */
    @Override
    public ResultBean countInPeriod(CountMessage message) throws ParseException {

        ResultBean resultBean = new ResultBean();
        resultBean.setDataList(countInPeriodUtil(message));
        resultBean.setSuccess(true);
        resultBean.setMessage("查询成功");

        return resultBean;
    }

    /**
     * 查询目标时间段内在该地点有操作的所有用户
     *
     * @param message 地点、目标时间段的开始和结束时间
     * @return
     */
    List<String> countInPeriodUtil(CountMessage message) throws ParseException {

        //sdf1为该时间点，sdf2为当天的零点
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        //获取目标时间段的开始时间和结束时间（开始结束为同一时间，则这个时间段就是这个时间点）
        Date startTime = sdf1.parse(message.getStartTime());
        Date endTime = sdf1.parse(message.getEndTime());
        //当天凌晨时间
        Date zeroTime = sdf2.parse(message.getStartTime());

        // description 查询在一段时间内某个建筑的所有新接入、断开、切换出、切换入的所有用户
        List<String> moveInfoList1 = moveInfoService.count1("%" + message.getLocation() + "%", startTime, endTime);

        // description 查询在当天凌晨0点到某一时间点前全部连接过用户Id (add  location_to)
        List<String> peopleIdList = moveInfoService.count2("%" + message.getLocation() + "%", zeroTime, endTime);

        List<String> moveInfoList2 = new LinkedList<>();
        for (String peopleId : peopleIdList) {
            //如果用户距离这个时间点最近的AP行为为add、location_from则表示已经接入当前点
            MoveInfo m = moveInfoService.sPoint(peopleId, zeroTime, startTime);
            if (m != null) {
                if (m.getLocation() != null && m.getApType().equals("增加") && m.getLocation().startsWith(message.getLocation())) {
                    moveInfoList2.add(m.getPeopleId());
                } else if (m.getLocationTo() != null && m.getLocationTo().startsWith(message.getLocation())) {
                    moveInfoList2.add(m.getPeopleId());
                }
            }
        }

        //求并集，因为当前时间段接入人数由两部分组成；这个时间段内接入人数 + 这个时间段内ap无操作但已经接入的用户
        moveInfoList1.removeAll(moveInfoList2);
        moveInfoList1.addAll(moveInfoList2);

        return moveInfoList1;
    }


    /**
     * 查询当前点接入的所有用户
     *
     * @param countMessage
     * @return
     * @throws ParseException
     */
    @Override
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
                if (m.getLocation() != null && m.getApType().equals("增加") && m.getLocation().startsWith(countMessage.getLocation())) {
                    moveInfoList1.add(m.getPeopleId());
                } else if (m.getLocationTo() != null && m.getLocationTo().startsWith(countMessage.getLocation())) {
                    moveInfoList1.add(m.getPeopleId());
                }
            }
        }

        //转换为前端需要的格式对应的JavaBean
        ResultBean resultBean = new ResultBean();
        resultBean.setDataList(moveInfoList1);
        resultBean.setMessage("查询成功");
        resultBean.setSuccess(true);

        return resultBean;
    }

    /**
     * 查询目标时间段所有建筑中Ap接入人数
     *
     * @param message 开始时间和结束时间
     * @return 各个地点的名称、接入人数和位置IP
     */
    @Override
    public ResultBean countInPeriodInAllBuildings(CountMessage message) throws ParseException {

        //获取所有建筑的名称
        List<String> buildingList = apDao.getBuildingList();
        //存储返回结果的列表
        List<CountPeopleMessage> countPeopleList = new LinkedList<>();

        // 对每一栋建筑查询相应时段的人数
        for (String building : buildingList) {
            //构造查询条件
            ApInfoProjection apInfo = apDao.getLatLngByBName(building);
            CountMessage m = new CountMessage(message.getStartTime(), message.getEndTime(), building);
            // 查询建筑内接入人数
            int num = countInPeriod(m).getDataList().size();
            //根据建筑名获取其经纬度
            ApInfoProjection a = apDao.getLatLngByBName(building);
            //将查询结果存入CountPeopleMessage类中
            CountPeopleMessage c = new CountPeopleMessage(building, num, a.getLat(), a.getLng());
            //将查询结果中的经纬度进行转换
            GPSUtil.bd_decryptNum(c);
            //查询结果存入列表中
            countPeopleList.add(c);
        }

        //转换为前端需要的格式对应的JavaBean
        ResultBean resultBean = new ResultBean();
        resultBean.setDataList(countPeopleList);
        resultBean.setSuccess(true);
        resultBean.setMessage("查询成功");

        return resultBean;
    }

    /**
     * 查询一个建筑内所有楼层的人数
     *
     * @param message 建筑名称、目标时间段的开始和结束时间段
     * @return 各个地点的名称、接入人数和位置IP
     */
    @Override
    public ResultBean queryInPeriodInABuilding(CountMessage message) throws ParseException {
        //存储返回结果的列表
        List<CountPeopleMessage> countPeopleList = new LinkedList<>();

        //根据建筑名字查询所有楼层
        List<String> layers = apDao.getLayers(message.getLocation());
        for (String layer : layers) {
            String location = message.getLocation() + " " + layer;
            CountMessage m = new CountMessage(message.getStartTime(), message.getEndTime(), location);
            //查询楼层内接入人数
            int num = countInPeriod(m).getDataList().size();
            CountPeopleMessage c = new CountPeopleMessage(location, num);
            //查询结果存入列表中
            countPeopleList.add(c);
        }

        //转换为前端需要的格式对应的JavaBean
        ResultBean resultBean = new ResultBean();
        resultBean.setDataList(countPeopleList);
        resultBean.setSuccess(true);
        resultBean.setMessage("查询成功");

        return resultBean;
    }

    /**
     * 查询一个楼层内所有房间的人数
     *
     * @param message 建筑名称、楼层、目标时间段的开始和结束时间段
     * @return 各个地点的名称、接入人数和位置IP
     */
    @Override
    public ResultBean queryInPeriodInALayer(CountMessage message) throws ParseException {
        //存储返回结果的列表
        List<CountPeopleMessage> countPeopleList = new LinkedList<>();

        String[] location0 = message.getLocation().split(" ");
        //根据建筑名字，楼层 查询所有房间
        List<String> rooms = apDao.getRooms(location0[0], location0[1]);

        for (String room : rooms) {
            String location = message.getLocation() + " " + room;
            CountMessage m = new CountMessage(message.getStartTime(), message.getEndTime(), location);
            //房间内接入人数
            int num = countInPeriod(m).getDataList().size();
            CountPeopleMessage c = new CountPeopleMessage(location, num);
            countPeopleList.add(c);
        }

        //转换为前端需要的格式对应的JavaBean
        ResultBean resultBean = new ResultBean();
        resultBean.setDataList(countPeopleList);
        resultBean.setSuccess(true);
        resultBean.setMessage("查询成功");

        return resultBean;
    }


}
