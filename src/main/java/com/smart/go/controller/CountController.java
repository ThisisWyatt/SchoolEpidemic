package com.smart.go.controller;

import com.smart.go.domain.MoveInfo;
import com.smart.go.service.impl.MoveInfoServiceImpl;
import com.smart.go.util.CountMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Description 统计实时的人数（较短时间间隔内）
 * Author cloudr
 * Date 2020/5/3 22:14
 * Version 1.0
 **/
@Controller
@RequestMapping("/count")
public class CountController {

    @Resource
    private MoveInfoServiceImpl moveInfoService;

    @RequestMapping("/conditionPage1")
    public String conditionPage(){
        return "conditionPage";
    }

    @ResponseBody
    @RequestMapping("/query")
    public String query(@ModelAttribute(value = "message") CountMessage message) throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startTime = simpleDateFormat.parse(message.getStartTime());
        Date endTime = simpleDateFormat.parse(message.getEndTime());
        String location=message.getLocation();

        // description 查询在一段时间内某个建筑的所有新接入、断开、切换出、切换入的所有用户
        List<MoveInfo> moveInfoList2=moveInfoService.count("%"+location+"%","%"+location+"%","%"+location+"%",startTime,endTime);
        System.out.println("列表长度为："+moveInfoList2.size());

        return "hello";
    }

}
