package com.smart.go.service;

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
    List<MoveInfo> count(String location, String location1, String location2, Date startTime, Date endTime);

}
