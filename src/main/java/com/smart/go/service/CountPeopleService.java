package com.smart.go.service;

import com.smart.go.domain.MoveInfo;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public interface CountPeopleService {

    List<String> countInPeriod(String startTime, String endTime, String location) throws ParseException;

    List<String> countAtPoint(String endTime, String location) throws ParseException;

}
