package com.smart.go.service;

import com.smart.go.domain.MoveInfo;
import com.smart.go.util.CountMessage;
import com.smart.go.util.ResultBean;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public interface CountPeopleService {

    // description 查询目标时间段内在该地点有操作的所有用户
    ResultBean countInPeriod(CountMessage message) throws ParseException;

    // description 查询当前点接入的所有用户
    ResultBean countAtPoint(CountMessage countMessage) throws ParseException;

    // description  查询目标时间段所有建筑中Ap接入人数
    ResultBean countInPeriodInAllBuildings(CountMessage message) throws ParseException;

}
