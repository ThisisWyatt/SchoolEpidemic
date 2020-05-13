package com.smart.go.service;

import com.smart.go.content.PathInfoProjection;
import com.smart.go.domain.MoveInfo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Description 用户
 * Author cloudr
 * Date 2020/5/3 23:15
 * Version 1.0
 **/

@Service
public interface MoveInfoService {

    // description 查询在一段时间内某个建筑的所有新接入、断开、切换出、切换入的所有用户
    List<String> count1(String location, Date startTime, Date endTime);

    // description 查询在一段时间内某个建筑的所有新接入、断开、切换出、切换入的所有用户
    List<String> count2(String location, Date startTime, Date endTime);

    // description  查询用户离当前时间最近的一次Ap连接信息
    MoveInfo sPoint(String peopleId, Date startTime, Date endTime);

    //根据Id查询出基本信息
    PathInfoProjection getOneById(String Id);

}
