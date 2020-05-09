package com.smart.go.service;

import com.smart.go.domain.MoveInfo;
import com.smart.go.util.CountMessage;
import com.smart.go.util.ResultBean;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public interface CountPeopleService {

    ResultBean countInPeriod(CountMessage message) throws ParseException;

    ResultBean countAtPoint(CountMessage countMessage) throws ParseException;

    ResultBean countInPeriodInAllBuildings(CountMessage message) throws ParseException;

}
