package com.smart.go.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.smart.go.content.CountMessage;
import com.smart.go.dao.ApDao;
import com.smart.go.service.impl.CountPeopleServiceImpl;
import com.smart.go.service.impl.MoveInfoServiceImpl;
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
 * Description 统计某个时间段内人数
 * Author wyatt
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
    @Resource
    private ApDao apDao;

    @RequestMapping("/conditionPage1")
    public String conditionPage() {
        return "conditionPage";
    }

    /**
     * 查询目标时间段所有建筑中Ap接入人数
     *
     * @param params 开始时间和结束时间
     * @return 各个地点的名称、接入人数和位置IP
     */
    @ResponseBody
    @RequestMapping("/queryInPeriodInAllBuilding")
    public ResultBean queryInPeriodInAllBuilding(@RequestBody String params) throws ParseException {

        //JSON格式转换为message类
        CountMessage message = JSON.parseObject(params, new TypeReference<CountMessage>() {
        });

        return countPeopleService.countInPeriodInAllBuildings(message);
    }

    /**
     * 查询一个建筑内所有楼层的人数
     *
     * @param params 建筑名称、目标时间段的开始和结束时间段
     * @return 各个地点的名称、接入人数和位置IP
     */
    @ResponseBody
    @RequestMapping("/queryInPeriodInABuilding")
    public ResultBean queryInPeriodInABuilding(@RequestBody String params) throws ParseException {
        CountMessage message = JSON.parseObject(params, new TypeReference<CountMessage>() {
        });

        return countPeopleService.queryInPeriodInABuilding(message);
    }

    /**
     * 查询一个楼层内所有房间及其人数
     *
     * @param params 建筑名称、楼层、目标时间段的开始和结束时间段
         * @return 各个地点的名称、接入人数和位置IP
     */
    @ResponseBody
    @RequestMapping("/queryInPeriodInALayer")
    public ResultBean queryInPeriodInALayer(@RequestBody String params) throws ParseException {
        CountMessage message = JSON.parseObject(params, new TypeReference<CountMessage>() {
        });

        return countPeopleService.queryInPeriodInALayer(message);
    }


    /**
     * 查询目标时间段内在该地点有操作的所有用户
     *
     * @param params 地点、目标时间段的开始和结束时间
     * @return 统计结果
     */
    @ResponseBody
    @RequestMapping("/queryInPeriod")
    public ResultBean queryInPeriod(@RequestBody String params) throws ParseException {

        CountMessage message = JSON.parseObject(params, new TypeReference<CountMessage>() {
        });

        return countPeopleService.countInPeriod(message);
    }

    /**
     * 查询当前点接入的所有用户
     *
     * @param params
     * @return
     * @throws ParseException
     */
    @ResponseBody
    @RequestMapping("/queryAtPoint")
    public ResultBean queryAtPoint(@RequestBody String params) throws ParseException {

        CountMessage countMessage = JSON.parseObject(params, new TypeReference<CountMessage>() {
        });
        logger.info("查询条件为" + countMessage);

        return countPeopleService.countAtPoint(countMessage);
    }

}
