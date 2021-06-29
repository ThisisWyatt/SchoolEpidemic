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

/**
 * Description 轨迹追踪控制层
 * Author wyatt
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

    /**
     *  根据人员Id查询其在某个时间段的轨迹信息
     * @param params  包含id、开始时间和结束时间的json对象
     * @return 包含人员id、姓名、department、所在位置、时间、校区等（参考前后端对接文档）
     */
    @ResponseBody
    @RequestMapping("/trackSinglePeople")
    public ResultBean trackSinglePeople(@RequestBody String params) throws ParseException {

        //前端请求的Json数据转为JavaBean对象
        TrackFromMessage message = JSON.parseObject(params, new TypeReference<TrackFromMessage>() {
        });

        ResultBean resultBean = trackPeopleService.trackSinglePeople(message);

        //如果返回结果为空，设置相应的响应信息
        if (resultBean.getDataList().size() == 0) {
            resultBean.setMessage("尚未查询到相关信息");
            resultBean.setSuccess(false);
        } else {
            resultBean.setMessage("查询成功");
            resultBean.setSuccess(true);
        }

        return resultBean;
    }


    /**
     * 根据人员Id查询在某个时间段接触过的人员基本信息
     * @param params 包含id、开始时间和结束时间的json对象
     * @return 包含接触人员的department、id和name
     */
    @ResponseBody
    @RequestMapping("/trackRelatedPeople")
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
