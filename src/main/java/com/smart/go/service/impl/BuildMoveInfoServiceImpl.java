package com.smart.go.service.impl;

import com.smart.go.content.ApInfoProjection1;
import com.smart.go.dao.ApDao;
import com.smart.go.dao.MoveInfoDao;
import com.smart.go.domain.MoveInfo;
import com.smart.go.service.BuildMoveInfoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Description
 * Author cloudr 将从日志文件中得到的aclog_result表的数据联合其他几张表存入move_info中
 * Date 2020/5/5 21:35
 * Version 1.0
 **/
@Service
public class BuildMoveInfoServiceImpl implements BuildMoveInfoService {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Resource
    private ApDao apDao;
    @Resource
    private MoveInfoDao moveInfoDao;

    @Override
    public void buildMoveInfo1() {

        Date dateToday0 = new Date();
        Date dateYesterday0 = new Date(new Date().getTime() - 86400000L);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {

            String dateToday1 = formatter.format(dateToday0);
            String dateYesterday1 = formatter.format(dateYesterday0);
            Date dateToday = formatter.parse(dateToday1);
            Date dateYesterday = formatter.parse(dateYesterday1);

            moveInfoDao.buildMoveInfo1(dateYesterday, dateToday);    //关联Ap增加、删除的情况
            moveInfoDao.buildMoveInfo2(dateYesterday, dateToday);    //关联Ap切换、漫游的情况

        } catch (ParseException e) {
            e.printStackTrace();
            logger.error("格式化当前时间日期格式错误");
        }


    }

    @Override
    public void buildMoveInfo2() {

        Date dateYesterday0 = new Date(new Date().getTime() - 86400000L);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dateYesterday = new Date();
        try {
            String dateYesterday1 = formatter.format(dateYesterday0);
            dateYesterday = formatter.parse(dateYesterday1);
        } catch (ParseException e) {
            e.printStackTrace();
            logger.error("格式化当前时间日期格式错误");
        }


        List<MoveInfo> moveInfoList = moveInfoDao.findAllAfter(dateYesterday);
        logger.info("长度为 " + moveInfoList.size());

        for (MoveInfo moveInfo : moveInfoList) {
            if (moveInfo.getApName() != null) {
                ApInfoProjection1 apInfo = apDao.getLocationAndCampus(moveInfo.getApName());
                if (apInfo != null) {
                    moveInfo.setLocation(apInfo.getLocation());
                    moveInfo.setCampus(apInfo.getCampus());
                    moveInfoDao.save(moveInfo);
//                    logger.info("存取成功");
                } else {
                    try {
                        moveInfoDao.delete(moveInfo);
                    } catch (Exception e) {
                        System.out.println(moveInfo);
                    }
                }
            } else {
                ApInfoProjection1 apInfoFrom = apDao.getLocationAndCampus(moveInfo.getApNameFrom());
                ApInfoProjection1 apInfoTo = apDao.getLocationAndCampus(moveInfo.getApNameTo());
                if (apInfoFrom != null && apInfoTo != null) {
                    moveInfo.setLocationFrom(apInfoFrom.getLocation());
                    moveInfo.setLocationTo(apInfoTo.getLocation());
                    moveInfo.setCampus(apInfoFrom.getCampus());
                    moveInfoDao.save(moveInfo);
//                    logger.warn("存取成功");
                } else {
                    moveInfoDao.delete(moveInfo);
                }
            }
        }
    }
}
