package com.smart.go.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
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

/**
 * Description
 * Author cloudr 轨迹追踪控制层
 * Date 2020/5/6 0:06
 * Version 1.0
 **/
@Controller
@RequestMapping("/track")
public class TrackController {
    // TODO 捕捉异常 增加日志

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
        ResultBean resultBean = trackPeopleService.trackSinglePeople(message);
        if (resultBean.getDataList().size() == 0) {
            resultBean.setMessage("尚未查询到相关信息");
            resultBean.setSuccess(false);
        } else {
            resultBean.setMessage("查询成功");
            resultBean.setSuccess(true);
        }

        return resultBean;
    }


    @ResponseBody
    @RequestMapping("/trackRelatedPeople")
    // description 根据人员Id查询在某个时间段接触过的人员基本信息
    public ResultBean trackRelatedPeople(@RequestBody String params) throws ParseException {
        TrackFromMessage message = JSON.parseObject(params, new TypeReference<TrackFromMessage>() {
        });


        ResultBean resultBean = trackPeopleService.trackRelatedPeople(message);
        if (resultBean.getDataList().size() == 0) {
            resultBean.setMessage("尚未查询到相关信息");
            resultBean.setSuccess(false);
        } else {
            resultBean.setMessage("查询成功");
            resultBean.setSuccess(true);
        }

        return resultBean;
    }


}
