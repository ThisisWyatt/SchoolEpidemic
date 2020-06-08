package com.smart.go.controller;

import com.smart.go.service.impl.BuildMoveInfoServiceImpl;
import com.smart.go.service.impl.ReadAndExactDataServiceImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.ParseException;

/**
 * Description
 * Author cloudr
 * Date 2020/6/9 1:49
 * Version 1.0
 **/
@Controller
public class DisposeData {

    @Resource
    private ReadAndExactDataServiceImpl readAndExactDataService;
    @Resource
    private BuildMoveInfoServiceImpl buildMoveInfo;

    @Scheduled(cron = "0 32 3 * * ? ")
        //每天1:30开始处理日志源文件
    void GetDataFromLog() throws IOException, ParseException {
        readAndExactDataService.TestReadLog();
    }

    @Scheduled(cron = "0 0 4 * * ? ")
        //每天1:30开始处理日志源文件
    void BuildMoveInfo() throws ParseException {
        buildMoveInfo.buildMoveInfo();
    }


}
