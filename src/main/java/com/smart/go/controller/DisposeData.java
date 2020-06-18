package com.smart.go.controller;

import com.smart.go.dao.SingleLogDao;
import com.smart.go.domain.SingleLog;
import com.smart.go.service.impl.BuildMoveInfoServiceImpl;
import com.smart.go.service.impl.ReadAndExactDataServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

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
    @Resource
    private SingleLogDao singleLogDao;


    @Scheduled(cron = "0 15 3 * * ? ")
        //每天1:30开始处理日志源文件
    void GetDataFromLog() {

        logger.info(new Date() + "日志原文件处理开始");

        readAndExactDataService.TestReadLog();

        logger.info(new Date() + "日志原文件处理结束");
    }

    @Scheduled(cron = "0 30 5 * * ? ")
        //每天1:30开始处理日志源文件
    void BuildMoveInfo() {

        logger.info(new Date() + "人员活动信息获取开始");


        buildMoveInfo.buildMoveInfo1(); //关联得到除开位置信息、校区信息的其他信息

        buildMoveInfo.buildMoveInfo2(); //关联得位置信息、校区信息的其他信息

        singleLogDao.truncateTableAcLogResult(); //清空日志表

        logger.info(new Date() + "人员活动信息获取结束");
    }


}
