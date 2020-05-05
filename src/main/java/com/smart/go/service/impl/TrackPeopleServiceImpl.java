package com.smart.go.service.impl;

import com.smart.go.dao.MoveInfoDao;
import com.smart.go.domain.MoveInfo;
import com.smart.go.service.TrackPeopleService;
import org.springframework.stereotype.Service;
import sun.java2d.pipe.SpanShapeRenderer;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public List<MoveInfo> trackSinglePeople(String id, String startTime0, String endTime0) throws ParseException {

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        Date startTime = sdf1.parse(startTime0);
        Date endTime = sdf1.parse(endTime0);
        return moveInfoDao.getByPeopleIdAndRecordTimeBetween(id, startTime, endTime);

    }
}
