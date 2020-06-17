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
        moveInfoDao.buildMoveInfo1();
        moveInfoDao.buildMoveInfo2();
    }

    @Override
    public void buildMoveInfo2() throws ParseException {

        Date date0 = new Date(new Date().getTime() - 950400000L);
        Date date = new Date(date0.getTime());

        List<MoveInfo> moveInfoList = moveInfoDao.findAllAfter(date);
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
