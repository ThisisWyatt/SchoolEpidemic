package com.smart.go.controller;

import com.smart.go.service.impl.BuildMoveInfoServiceImpl;
import com.smart.go.service.impl.ReadAndExactDataServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Resource
    private ReadAndExactDataServiceImpl readAndExactDataService;
    @Resource
    private BuildMoveInfoServiceImpl buildMoveInfo;


    @Scheduled(cron = "0 32 3 * * ? ")
        //每天1:30开始处理日志源文件
    void GetDataFromLog() throws IOException, ParseException {
        readAndExactDataService.TestReadLog();
    }

    @Scheduled(cron = "0 52 0 * * ? ")
        //每天1:30开始处理日志源文件
    void BuildMoveInfo() throws ParseException {
        try {
            buildMoveInfo.buildMoveInfo1();
            buildMoveInfo.buildMoveInfo2();
        } catch (ParseException e) {
            logger.error("当前日期出错解析");
        }

    }


}
