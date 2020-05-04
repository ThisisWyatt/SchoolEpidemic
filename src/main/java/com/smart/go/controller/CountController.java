package com.smart.go.controller;

import com.smart.go.domain.MoveInfo;
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
    public String conditionPage() {
        return "conditionPage";
    }

    @ResponseBody
    @RequestMapping("/queryInPeriod")
    public String queryInPeriod(@ModelAttribute(value = "message") CountMessage message) throws ParseException {

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date startTime = sdf1.parse(message.getStartTime());
        Date endTime = sdf1.parse(message.getEndTime());
        Date zeroTime = sdf2.parse(message.getStartTime());//当天凌晨时间

        String location = message.getLocation();

        // description 查询在一段时间内某个建筑的所有新接入、断开、切换出、切换入的所有用户
        List<MoveInfo> moveInfoList1 = moveInfoService.count1("%" + location + "%", startTime, endTime);
        System.out.println("列表1长度为：" + moveInfoList1.size());

        // description 查询在当天凌晨0点到某一时间点前全部连接过用户Id (add  location_to)
        List<String> peopleIdList = moveInfoService.count2("%" + location + "%", zeroTime, endTime);
        List<MoveInfo> moveInfoList2 = new LinkedList<>();
        for (String peopleId : peopleIdList) {
            //如果用户距离这个时间点最近的AP行为为add、location_from则表示已经接入当前点
            MoveInfo m = moveInfoService.sPoint(peopleId, startTime, endTime);
            if (m != null) {
                if (m.getLocation() != null && m.getApType().equals("增加") && m.getLocation().startsWith(location))
                    moveInfoList2.add(m);
                else if (m.getLocationTo() != null && m.getLocationTo().startsWith(location))
                    moveInfoList2.add(m);
            }
        }

        //求并集
        moveInfoList1.removeAll(moveInfoList2);
        moveInfoList1.addAll(moveInfoList2);

        return String.valueOf(moveInfoList1.size());
    }

    @ResponseBody
    @RequestMapping("/queryAtPoint")
    // description 查询当前点接入的所有用户 (add  location_from)
    public String queryAtPoint(@ModelAttribute(value = "message") CountMessage message) throws ParseException {

        //当天凌晨时间
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        Date startTime = sdf1.parse(message.getStartTime());
        //目标时间点
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date endTime = sdf2.parse(message.getStartTime());

        String location = message.getLocation();

        //查询当天凌晨0点到当天某一时间点前某个地点所有 接入、切换入的所有用户ID (add  location_to)
        List<String> peopleIdList = moveInfoService.count2("%" + location + "%", startTime, endTime);
        List<MoveInfo> moveInfoList = new LinkedList<>();
        System.out.println("peopleIdList.size()=" + peopleIdList.size());
        for (String peopleId : peopleIdList) {
            //如果用户距离这个时间点最近的AP行为为add、location_from则表示已经接入当前点
            MoveInfo m = moveInfoService.sPoint(peopleId, startTime, endTime);
            if (m != null) {
                if (m.getLocation() != null && m.getApType().equals("增加") && m.getLocation().startsWith(location))
                    moveInfoList.add(m);
                else if (m.getLocationTo() != null && m.getLocationTo().startsWith(location))
                    moveInfoList.add(m);
            }
        }

        for (MoveInfo m : moveInfoList) {
            System.out.println(m);
        }

        return String.valueOf(moveInfoList.size());
    }


}
