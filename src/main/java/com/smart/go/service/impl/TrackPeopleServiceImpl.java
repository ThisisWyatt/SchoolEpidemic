package com.smart.go.service.impl;

import com.smart.go.content.*;
import com.smart.go.dao.ApDao;
import com.smart.go.dao.MoveInfoDao;
import com.smart.go.domain.MoveInfo;
import com.smart.go.service.TrackPeopleService;
import com.smart.go.util.GPSUtil;
import com.smart.go.util.ResultBean;
import com.smart.go.util.TrackFromMessage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.Path;
import javax.validation.constraints.Null;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Description
 * Author cloudr  轨迹追踪业务层接口实现
 * Date 2020/5/6 0:58
 * Version 1.0
 **/
@Service
public class TrackPeopleServiceImpl implements TrackPeopleService {

    @Resource
    private MoveInfoDao moveInfoDao;
    @Resource
    private CountPeopleServiceImpl countPeopleService;
    @Resource
    private MoveInfoServiceImpl moveInfoService;
    @Resource
    private ApDao apDao;

    @Override
    // description 根据人员Id查询在某个时间段的ap连接信息
    public ResultBean trackSinglePeople(TrackFromMessage message) throws ParseException {

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startTime = sdf1.parse(message.getStartTime());
        Date endTime = sdf1.parse(message.getEndTime());

        List<MoveInfo> moveInfoList = moveInfoDao.getByPeopleIdAndRecordTimeBetween(message.getId(), startTime, endTime);
        List<PathInfo> pathInfoList = new LinkedList<>();
        for (int i = 1; i < moveInfoList.size(); i++) {
            MoveInfo m1 = moveInfoList.get(i - 1);
            MoveInfo m2 = moveInfoList.get(i);

            Date time1 = m1.getRecordTime();
            Date time2 = m2.getRecordTime();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");

            long diff = time2.getTime() - time1.getTime();
            long diffMinutes = diff / (60 * 1000) % 60;
            //如果前后两条记录时间小于5分钟则认为是路过 不计算入行为轨迹
            if (diffMinutes >= 5) {
                if (m1.getLocation() != null) {
                    ApInfoProjection a = apDao.getLatLngByBName(m1.getLocation().substring(0, m1.getLocation().indexOf(" "))); //获取位置所在建筑物的地理坐标
                    PathInfo p = new PathInfo(m1.getPeopleId(), m1.getName(), m1.getDepartment(), m1.getLocation(), dateFormat.format(m1.getRecordTime()), a.getLat(), a.getLng(), m1.getCampus());
                    GPSUtil.forPathInfo(p); //坐标转换
                    pathInfoList.add(p);

                } else {
                    ApInfoProjection a1 = apDao.getLatLngByBName(m1.getLocationFrom().substring(0, m1.getLocationFrom().indexOf(" ")));
                    ApInfoProjection a2 = apDao.getLatLngByBName(m1.getLocationTo().substring(0, m1.getLocationTo().indexOf(" ")));
                    PathInfo p1 = new PathInfo(m1.getPeopleId(), m1.getName(), m1.getDepartment(), m1.getLocationFrom(), dateFormat.format(m1.getRecordTime()), a1.getLat(), a1.getLng(), m1.getCampus());
                    PathInfo p2 = new PathInfo(m1.getPeopleId(), m1.getName(), m1.getDepartment(), m1.getLocationTo(), dateFormat.format(m1.getRecordTime()), a2.getLat(), a2.getLng(), m1.getCampus());
                    GPSUtil.forPathInfo(p1);//坐标转换
                    GPSUtil.forPathInfo(p2);
                    pathInfoList.add(p1);
                    pathInfoList.add(p1);
                }
            }
        }

        ResultBean resultBean = new ResultBean();
        resultBean.setDataList(pathInfoList);
        resultBean.setMessage("查询成功");
        resultBean.setSuccess(true);

        return resultBean;
    }


    private List<PathInfo1> trackSinglePeopleByPeriodUtil(TrackFromMessage message) throws ParseException {

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startTime = sdf1.parse(message.getStartTime());
        Date endTime = sdf1.parse(message.getEndTime());

        List<MoveInfo> moveInfoList = moveInfoDao.getByPeopleIdAndRecordTimeBetween(message.getId(), startTime, endTime);
        List<PathInfo1> pathInfoList = new LinkedList<>();
        for (int i = 1; i < moveInfoList.size(); i++) {
            MoveInfo m1 = moveInfoList.get(i - 1);
            MoveInfo m2 = moveInfoList.get(i);

            Date time1 = m1.getRecordTime();
            Date time2 = m2.getRecordTime();

            long diff = time2.getTime() - time1.getTime();
            long diffMinutes = diff / (60 * 1000) % 60;
            //如果前后两条记录时间小于5分钟则认为是路过 不计算入行为轨迹
            if (diffMinutes >= 5) {
                String locationFrom = m1.getLocation() != null ? m1.getLocation() : m1.getLocationTo();
                String locationTo = m2.getLocation() != null ? m2.getLocation() : m2.getLocationTo();
                pathInfoList.add(new PathInfo1(m1.getPeopleId(), m1.getName(), m1.getDepartment(), locationFrom, locationTo, m1.getRecordTime(), m2.getRecordTime()));
            }
        }

        return pathInfoList;
    }


    @Override
    // description 根据人员Id查询在某个时间段接触过的人员Id
    public ResultBean trackRelatedPeople(TrackFromMessage message) throws ParseException {
        ResultBean resultBean = new ResultBean();

        List<String> relatePeopleList = new LinkedList<>();

        // start设置为endTime前14天
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        Date endTime1 = format.parse(message.getEndTime());
        long length = endTime1.getTime();
        long subtractLength = 14 * 24 * 60 * 60 * 1000;
        Date startTime1 = new Date();
        startTime1.setTime(length - subtractLength);
        message.setStartTime(format.format(startTime1));

        List<PathInfo1> pathInfoList = trackSinglePeopleByPeriodUtil(message);
        for (PathInfo1 singlePoint : pathInfoList) {

            String locationFrom = singlePoint.getLocationFrom();
            String locationTo = singlePoint.getLocationFrom();
            Date startTime = singlePoint.getStartTime();
            Date endTime = singlePoint.getEndTime();
            ApInfoProjection a1 = apDao.getLatLngByBName(locationFrom);
            CountMessage message1 = new CountMessage(startTime.toString(), endTime.toString(), locationFrom);
            List<String> relateList1 = countPeopleService.countInPeriodUtil(message1);

            List<String> relateList2 = new LinkedList<>(); //如果LocationFrom和LocationTo不是同一个地点则再查询一次
            if (!singlePoint.getLocationFrom().equals(singlePoint.getLocationTo())) {
                ApInfoProjection a2 = apDao.getLatLngByBName(locationTo);
                CountMessage message2 = new CountMessage(startTime.toString(), endTime.toString(), locationTo);
                relateList2 = countPeopleService.countInPeriodUtil(message2);
            }

            //求并集
            relateList1.removeAll(relateList2);
            relateList1.addAll(relateList2);

            relatePeopleList.addAll(relateList1);

        }
        // 排除自身
        relatePeopleList.removeIf(o -> o.equals(message.getId()));

        //去重
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>(relatePeopleList);
        relatePeopleList = new LinkedList<>(linkedHashSet);
        List<PathInfoProjection> relatePeopleListInfo = new LinkedList<>();
        for (String s : relatePeopleList) {
            //根据Id查询出基本信息
            relatePeopleListInfo.add(moveInfoService.getOneById(s));
        }

        resultBean.setDataList(relatePeopleListInfo);
        return resultBean;
    }
}
