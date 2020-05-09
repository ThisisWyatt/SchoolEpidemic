package com.smart.go.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.smart.go.dao.ApDao;
import com.smart.go.service.impl.CountPeopleServiceImpl;
import com.smart.go.service.impl.MoveInfoServiceImpl;
import com.smart.go.content.CountMessage;
import com.smart.go.util.ResultBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;

/**
 * Description 统计人数
 * Author cloudr
 * Date 2020/5/3 22:14
 * Version 1.0
 **/
@Controller
@RequestMapping("/count")
public class CountController {

    private Logger logger = LogManager.getLogger(this.getClass());


    @Resource
    private MoveInfoServiceImpl moveInfoService;
    @Resource
    private CountPeopleServiceImpl countPeopleService;

    @RequestMapping("/conditionPage1")
    public String conditionPage() {
        return "conditionPage";
    }

    @Resource
    private ApDao apDao;

    @ResponseBody
    @RequestMapping("/queryInPeriodInAllBuilding")
    // description  查询目标时间段所有建筑中Ap接入人数
    public ResultBean queryInPeriodInAllBuilding(@RequestBody String params) throws ParseException {

        CountMessage message = JSON.parseObject(params, new TypeReference<CountMessage>() {
        });

        return countPeopleService.countInPeriodInAllBuildings(message);
    }

    @ResponseBody
    @RequestMapping("/queryInPeriod")
    // description 查询目标时间段内在该地点有操作的所有用户
    public ResultBean queryInPeriod(@RequestBody String params) throws ParseException {

        CountMessage message = JSON.parseObject(params, new TypeReference<CountMessage>() {
        });

        return countPeopleService.countInPeriod(message);
    }

    @ResponseBody
    @RequestMapping("/queryAtPoint")
    // description 查询当前点接入的所有用户
    public ResultBean queryAtPoint(@RequestBody String params) throws ParseException {

        CountMessage countMessage = JSON.parseObject(params, new TypeReference<CountMessage>() {
        });
        logger.info("查询条件为" + countMessage);

        return countPeopleService.countAtPoint(countMessage);
    }

}
