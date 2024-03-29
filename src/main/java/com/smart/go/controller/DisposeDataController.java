package com.smart.go.controller;

import com.smart.go.dao.SingleLogDao;
import com.smart.go.service.impl.BuildMoveInfoServiceImpl;
import com.smart.go.service.impl.ReadAndExactDataServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Description 处理数据
 * Author wyatt
 * Date 2020/6/9 1:49
 * Version 1.0
 **/
@Controller
public class DisposeDataController {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Resource
    private ReadAndExactDataServiceImpl readAndExactDataService;
    @Resource
    private BuildMoveInfoServiceImpl buildMoveInfo;
    @Resource
    private SingleLogDao singleLogDao;


    /**
     * 每天1:30开始处理日志源文件
     */
    @Scheduled(cron = "0 30 1 * * ? ")
    void GetDataFromLog() {

        logger.info(new Date() + "日志原文件处理开始");

        readAndExactDataService.TestReadLog();

        logger.info(new Date() + "日志原文件处理结束");
    }

    /**
     * 每天3:30开始融合表获取人员ap连接信息
     */
    @Scheduled(cron = "0 30 3 * * ? ")
    void BuildMoveInfo() {

        logger.info(new Date() + "人员活动信息获取开始");

        buildMoveInfo.buildMoveInfo1();         //关联得到除开位置信息、校区信息的其他信息

        buildMoveInfo.buildMoveInfo2();         //关联得位置信息、校区信息的其他信息

        singleLogDao.truncateTableAcLogResult(); //清空日志表

        logger.info(new Date() + "人员活动信息获取结束");
    }


}
