package com.smart.go.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.smart.go.content.PathInfo;
import com.smart.go.service.impl.CountPeopleServiceImpl;
import com.smart.go.service.impl.TrackPeopleServiceImpl;
import com.smart.go.util.ResultBean;
import com.smart.go.util.TrackFromMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Description
 * Author cloudr 轨迹追踪控制层
 * Date 2020/5/6 0:06
 * Version 1.0
 **/
@Controller
@RequestMapping("/track")
public class TrackController {

    @Resource
    private TrackPeopleServiceImpl trackPeopleService;

    @RequestMapping("/trackConditionPage")
    public String trackConditionPage() {
        return "trackPage";
    }

    @ResponseBody
    @RequestMapping("/trackSinglePeople")
    // description 根据人员Id查询在某个时间段的轨迹信息
    public ResultBean trackSinglePeople(@RequestBody String params) throws ParseException {

        TrackFromMessage message = JSON.parseObject(params, new TypeReference<TrackFromMessage>() {
        });

        return trackPeopleService.trackSinglePeople(message);
    }


    @ResponseBody
    @RequestMapping("/trackRelatedPeople")
    // description 根据人员Id查询在某个时间段接触过的人员Id
    public ResultBean trackRelatedPeople(@RequestBody String params) throws ParseException {
        TrackFromMessage message = JSON.parseObject(params, new TypeReference<TrackFromMessage>() {
        });

        return trackPeopleService.trackRelatedPeople(message);
    }


}
