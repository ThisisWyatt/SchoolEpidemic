package com.smart.go.controller;

import com.smart.go.content.CountPeople;
import com.smart.go.dao.ApDao;
import com.smart.go.domain.MoveInfo;
import com.smart.go.domain.apProjection;
import com.smart.go.service.impl.CountPeopleServiceImpl;
import com.smart.go.service.impl.MoveInfoServiceImpl;
import com.smart.go.util.CountMessage;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Description 统计人数
 * Author cloudr
 * Date 2020/5/3 22:14
 * Version 1.0
 **/
@Controller
@RequestMapping("/count")
public class CountController {

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
    public String queryInPeriodInAllBuilding(@ModelAttribute(value = "message") CountMessage message) throws ParseException {
        String startTime = message.getStartTime();
        String endTime = message.getEndTime();

        List<String> buildingList = apDao.getBuildingList();
        List<CountPeople> countPeopleList = new LinkedList<>();

        for (String building : buildingList) {
            int num = countPeopleService.countInPeriod(startTime, endTime, building).size();
            countPeopleList.add(new CountPeople(building, num));
        }

        for (CountPeople c : countPeopleList)
            System.out.println(c);

        return null;
    }

    @ResponseBody
    @RequestMapping("/queryInPeriod")
    // description 查询目标时间段内在该地点有操作的所有用户
    public String queryInPeriod(@ModelAttribute(value = "message") CountMessage message) throws ParseException {

        List<String> countPeopleInPeriod = countPeopleService.countInPeriod(message.getStartTime(), message.getEndTime(), message.getLocation());

        for (String m : countPeopleInPeriod)
            System.out.println(m);

        return String.valueOf(countPeopleInPeriod.size());

    }

    @ResponseBody
    @RequestMapping("/queryAtPoint")
    // description 查询当前点接入的所有用户 (add  location_from)
    public String queryAtPoint(@ModelAttribute(value = "message") CountMessage message) throws ParseException {

        List<String> PeopleIdList = countPeopleService.countAtPoint(message.getStartTime(), message.getLocation());

        return String.valueOf(PeopleIdList.size());

    }

}
