package com.smart.go.service.impl;

import com.smart.go.content.*;
import com.smart.go.dao.ApDao;
import com.smart.go.dao.MoveInfoDao;
import com.smart.go.service.TrackPeopleService;
import com.smart.go.util.GPSUtil;
import com.smart.go.util.ResultBean;
import com.smart.go.util.TrackFromMessage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Description 轨迹追踪业务层接口实现
 * Author wyatt
 * Date 2020/5/6 0:58
 * Version 1.0
 */
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

    /**
     * 根据人员Id查询在某个时间段的ap连接信息
     *
     * @param message 包含id、开始时间和结束时间的JavaBean
     * @return data为包含人员id、姓名、department、所在位置、时间、校区等（参考前后端对接文档）的JavaBean(PathInfo)的ResultBean
     */
    @Override
    public ResultBean trackSinglePeople(TrackFromMessage message) throws ParseException {

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //查询时间段的范围
        Date startTime = sdf2.parse(message.getStartTime());
        Date endTime = sdf2.parse(message.getEndTime());
        //根据人员Id查询在某个时间段的ap连接信息
        List<MoveInfoProjection> moveInfoList = moveInfoDao.findInPeriod(message.getId(), startTime, endTime);
        //存储轨迹信息结果的list
        List<PathInfo> pathInfoList = new LinkedList<>();

        for (int i = 1; i < moveInfoList.size(); i++) {
            //前一次连接信息
            MoveInfoProjection m1 = moveInfoList.get(i - 1);
            //本次连接信息
            MoveInfoProjection m2 = moveInfoList.get(i);

            //前一次的连接时间
            Date time1 = m1.getRecordTime();
            //本次的连接时间
            Date time2 = m2.getRecordTime();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");

            //计算前后两次连接时间差
            long diff = time2.getTime() - time1.getTime();
            long diffMinutes = diff / (60 * 1000) % 60;

            //如果前后两条记录时间小于5分钟则认为是路过 不计算入行为轨迹
            if (diffMinutes >= 5) {
                //增加或者删除Ap
                if (m1.getLocation() != null) {
                    //获取位置所在建筑物的地理坐标
                    ApInfoProjection a = apDao.getLatLngByBName(m1.getLocation().substring(0, m1.getLocation().indexOf(" ")));
                    PathInfo p = new PathInfo(m1.getPeopleId(), m1.getName(), m1.getDepartment(), m1.getLocation(), dateFormat.format(m1.getRecordTime()), a.getLat(), a.getLng(), m1.getCampus());
                    //坐标转换
                    GPSUtil.forPathInfo(p);
                    pathInfoList.add(p);

                }
                //切换或者漫游
                else {
                    //根据建筑名获取其经纬度
                    ApInfoProjection a1 = apDao.getLatLngByBName(m1.getLocationFrom().substring(0, m1.getLocationFrom().indexOf(" ")));
                    ApInfoProjection a2 = apDao.getLatLngByBName(m1.getLocationTo().substring(0, m1.getLocationTo().indexOf(" ")));
                    PathInfo p1 = new PathInfo(m1.getPeopleId(), m1.getName(), m1.getDepartment(), m1.getLocationFrom(), dateFormat.format(m1.getRecordTime()), a1.getLat(), a1.getLng(), m1.getCampus());
                    PathInfo p2 = new PathInfo(m1.getPeopleId(), m1.getName(), m1.getDepartment(), m1.getLocationTo(), dateFormat.format(m1.getRecordTime()), a2.getLat(), a2.getLng(), m1.getCampus());
                    //坐标转换
                    GPSUtil.forPathInfo(p1);
                    GPSUtil.forPathInfo(p2);
                    pathInfoList.add(p1);
                    pathInfoList.add(p1);
                }
            }
        }

        //去重
        LinkedHashSet<PathInfo> pathInfoListH = new LinkedHashSet<>(pathInfoList);
        pathInfoList = new LinkedList<>(pathInfoListH);

        ResultBean resultBean = new ResultBean();
        resultBean.setDataList(pathInfoList);
        resultBean.setMessage("查询成功");
        resultBean.setSuccess(true);

        return resultBean;
    }

    /**
     * 追踪单个人员在某个时间段内的轨迹(待过的地点)
     *
     * @param message 人员id, 起止时间
     * @return 待过的地点
     */
    private List<PathInfo1> trackSinglePeopleByPeriodUtil(TrackFromMessage message) throws ParseException {

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //起始值时间段
        Date startTime = sdf2.parse(message.getStartTime());
        Date endTime = sdf2.parse(message.getEndTime());

        //根据人员Id查询在某个时间段的ap连接信息
        List<MoveInfoProjection> moveInfoList = moveInfoDao.findInPeriod(message.getId(), startTime, endTime);
        //存储轨迹信息结果的list
        List<PathInfo1> pathInfoList = new LinkedList<>();

        for (int i = 1; i < moveInfoList.size(); i++) {
            MoveInfoProjection m1 = moveInfoList.get(i - 1);
            MoveInfoProjection m2 = moveInfoList.get(i);

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


    /**
     * 根据人员Id查询在某个时间段接触过的人员Id
     *
     * @param message 包含id、开始时间和结束时间的JavaBean
     * @return data为接触人员信息的resultBean
     */
    @Override
    public ResultBean trackRelatedPeople(TrackFromMessage message) throws ParseException {
        ResultBean resultBean = new ResultBean();

        //存储查询到的接触人员的list
        List<String> relatePeopleList = new LinkedList<>();

        // start设置为endTime前14天
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        //查询时间段的endTime为请求参数中获取到的
        Date endTime1 = format.parse(message.getEndTime());
        long length = endTime1.getTime();
        //最多查询到14天内接触过的人员
        long subtractLength = 14 * 24 * 60 * 60 * 1000;

        //查询时间段的startTime为endTime往前推14天
        Date startTime1 = new Date();
        startTime1.setTime(length - subtractLength);
        message.setStartTime(format.format(startTime1));

        //单个人员在某个时间段内的轨迹(待过的地点)
        List<PathInfo1> pathInfoList = trackSinglePeopleByPeriodUtil(message);

        for (PathInfo1 singlePoint : pathInfoList) {
            //查询地点的切换的变更前位置和变更后位置（如果变更的话）
            String locationFrom = singlePoint.getLocationFrom();
            String locationTo = singlePoint.getLocationFrom();
            //查询时间的起始和结束时间
            Date startTime = singlePoint.getStartTime();
            Date endTime = singlePoint.getEndTime();

            //查询目标时间段内在该地点有操作的所有用户
            CountMessage message1 = new CountMessage(startTime.toString(), endTime.toString(), locationFrom);
            List<String> relateList1 = countPeopleService.countInPeriodUtil(message1);

            //如果LocationFrom和LocationTo不是同一个地点则再查询一次，因为此时可能在两个地方都有接触
            List<String> relateList2 = new LinkedList<>();
            if (!singlePoint.getLocationFrom().equals(singlePoint.getLocationTo())) {
                //查询目标时间段内在该地点有操作的所有用户
                CountMessage message2 = new CountMessage(startTime.toString(), endTime.toString(), locationTo);
                relateList2 = countPeopleService.countInPeriodUtil(message2);
            }

            //求并集，可能接触的人员在俩地方同时出现
            relateList1.removeAll(relateList2);
            relateList1.addAll(relateList2);

            relatePeopleList.addAll(relateList1);

        }
        // 排除自身
        relatePeopleList.removeIf(o -> o.equals(message.getId()));

        //去重，可能接触的人员多次出现
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>(relatePeopleList);
        relatePeopleList = new LinkedList<>(linkedHashSet);

        //存储所接触人员的信息
        List<PathInfoProjection> relatePeopleListInfo = new LinkedList<>();
        for (String s : relatePeopleList) {
            //根据Id查询出基本信息
            relatePeopleListInfo.add(moveInfoService.getOneById(s));
        }

        //查询结果存入resultBean中
        resultBean.setDataList(relatePeopleListInfo);
        return resultBean;
    }
}
