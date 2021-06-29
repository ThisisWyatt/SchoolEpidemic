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

    /**
     * 根据人员Id查询在某个时间段的ap连接信息
     *
     * @param message 包含id、开始时间和结束时间的JavaBean
     * @return data为包含人员id、姓名、department、所在位置、时间、校区等（参考前后端对接文档）的JavaBean(PathInfo)的ResultBean
     */
    ResultBean trackSinglePeople(TrackFromMessage message) throws ParseException;

    /**
     * 根据人员Id查询在某个时间段接触过的人员Id
     *
     * @param message 包含id、开始时间和结束时间的JavaBean
     * @return data为接触人员信息的resultBean
     */
    ResultBean trackRelatedPeople(TrackFromMessage message) throws ParseException;
}
