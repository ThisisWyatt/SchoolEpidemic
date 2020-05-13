package com.smart.go.service;

import com.smart.go.util.ResultBean;
import com.smart.go.util.TrackFromMessage;
import org.springframework.stereotype.Service;

import java.text.ParseException;

/**
 * Description
 * Author cloudr 轨迹追踪业务层接口
 * Date 2020/5/6 0:58
 * Version 1.0
 **/

@Service
public interface TrackPeopleService {

    // description 根据人员Id查询在某个时间段的ap连接信息
    ResultBean trackSinglePeople(TrackFromMessage message) throws ParseException;

    // description 根据人员Id查询在某个时间段接触过的人员Id
    ResultBean trackRelatedPeople(TrackFromMessage message) throws ParseException;
}
