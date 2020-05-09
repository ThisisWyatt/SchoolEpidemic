package com.smart.go.controller;

import com.smart.go.content.PathInfo;
import com.smart.go.content.relatePeople;
import com.smart.go.domain.MoveInfo;
import com.smart.go.service.impl.CountPeopleServiceImpl;
import com.smart.go.service.impl.TrackPeopleServiceImpl;
import com.smart.go.util.ResultBean;
import com.smart.go.util.TrackFromMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.*;

/**
 * Description
 * Author cloudr 轨迹追踪控制层
 * Date 2020/5/6 0:06
 * Version 1.0
 **/
@Controller
@RequestMapping("/track")
public class TrackController {

//    @Resource
//    private TrackPeopleServiceImpl trackPeopleService;
//
//    @RequestMapping("/trackConditionPage")
//    public String trackConditionPage() {
//        return "trackPage";
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/trackSinglePeople", method = RequestMethod.POST)
//    // description 根据人员Id查询在某个时间段的轨迹信息
//    public ResultBean trackSinglePeople(TrackFromMessage message) throws ParseException {
//
//        List<PathInfo> pathInfoList = trackPeopleService.trackSinglePeople(message.getId(), message.getStartTime(), message.getEndTime());
//
//        ResultBean resultBean=new ResultBean();
//        resultBean.setDataList(pathInfoList);
//
//        return resultBean;
//    }
//
//    @Resource
//    private CountPeopleServiceImpl countPeopleService;
//
//    @ResponseBody
//    @RequestMapping(value = "/trackRelatedPeople", method = RequestMethod.POST)
//    // description 根据人员Id查询在某个时间段接触过的人员Id
//    public ResultBean trackRelatedPeople(TrackFromMessage message) throws ParseException {
//
//        List<String> relatePeopleList = new LinkedList<>();
//        List<PathInfo> pathInfoList = trackPeopleService.trackSinglePeople(message.getId(), message.getStartTime(), message.getEndTime());
//        for (PathInfo singlePoint : pathInfoList) {
//            String location = singlePoint.getLocationFrom();
//            Date startTime = singlePoint.getStartTime();
//            Date endTime = singlePoint.getEndTime();
//            List<String> relateList1 = countPeopleService.countInPeriod(startTime.toString(), endTime.toString(), location);
//
//            List<String> relateList2 = new LinkedList<>(); //如果LocationFrom和LocationTo不是同一个地点则再查询一次
//            if (!singlePoint.getLocationFrom().equals(singlePoint.getLocationTo()))
//                relateList2 = countPeopleService.countInPeriod(startTime.toString(), endTime.toString(), location);
//
//            //求并集
//            relateList1.removeAll(relateList2);
//            relateList1.addAll(relateList2);
//
//            relatePeopleList.addAll(relateList1);
//        }
//
//        // 排除自身
//        relatePeopleList.removeIf(o -> o.equals(message.getId()));
//
//        //去重
//        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>(relatePeopleList);
//        relatePeopleList = new LinkedList<>(linkedHashSet);
//
//        for (String id : relatePeopleList)
//            System.out.println(id);
//
//
//        ResultBean resultBean=new ResultBean();
//        resultBean.setDataList(relatePeopleList);
//        return resultBean;
//    }


}
