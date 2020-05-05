package com.smart.go.controller;

import com.smart.go.domain.MoveInfo;
import com.smart.go.service.impl.TrackPeopleServiceImpl;
import com.smart.go.util.TrackFromMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;
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
    @RequestMapping(value = "/trackSinglePeople", method = RequestMethod.POST)
    // description 根据人员Id查询在某个时间段的ap连接信息
    public String trackSinglePeople(TrackFromMessage message) throws ParseException {

        List<MoveInfo> list1 = trackPeopleService.trackSinglePeople(message.getId(), message.getStartTime(), message.getEndTime());
        for (MoveInfo m : list1)
            System.out.println(m);
        return message.getId();
    }


}
