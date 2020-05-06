package com.smart.go.service.impl;

import com.smart.go.content.PathInfo;
import com.smart.go.dao.MoveInfoDao;
import com.smart.go.domain.MoveInfo;
import com.smart.go.service.TrackPeopleService;
import org.springframework.stereotype.Service;
import sun.java2d.pipe.SpanShapeRenderer;

import javax.annotation.Resource;
import java.rmi.MarshalledObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Override
    // description 根据人员Id查询在某个时间段的ap连接信息
    public List<PathInfo> trackSinglePeople(String id, String startTime0, String endTime0) throws ParseException {

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startTime = sdf1.parse(startTime0);
        Date endTime = sdf1.parse(endTime0);

        List<MoveInfo> moveInfoList = moveInfoDao.getByPeopleIdAndRecordTimeBetween(id, startTime, endTime);
        List<PathInfo> pathInfoList = new LinkedList<>();
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
                pathInfoList.add(new PathInfo(locationFrom, locationTo, m1.getRecordTime(), m2.getRecordTime()));

            }

        }
        return pathInfoList;
    }
}
