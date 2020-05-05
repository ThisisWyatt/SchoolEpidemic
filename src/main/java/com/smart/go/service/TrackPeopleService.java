package com.smart.go.service;

import com.smart.go.domain.MoveInfo;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

/**
 * Description
 * Author cloudr 轨迹追踪业务层接口
 * Date 2020/5/6 0:58
 * Version 1.0
 **/

@Service
public interface TrackPeopleService {

    // description 根据人员Id查询在某个时间段的ap连接信息
    List<MoveInfo> trackSinglePeople(String id, String startTime0, String endTime0) throws ParseException;
}
