package com.smart.go.service.impl;

import com.smart.go.dao.*;
import com.smart.go.domain.*;
import com.smart.go.service.BuildMoveInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Description
 * Author cloudr 将从日志文件中得到的aclog_result表的数据联合其他几张表存入move_info中
 * Date 2020/5/5 21:35
 * Version 1.0
 **/
@Service
public class BuildMoveInfoImpl implements BuildMoveInfo {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Resource
    private SingleLogDao singleLogDao;
    @Resource
    private StudentDao studentDao;
    @Resource
    private ApUserDao apUserDao;
    @Resource
    private ApDao apDao;
    @Resource
    private MoveInfoDao moveInfoDao;
    @Resource
    private TeacherDao teacherDao;


    @Override
    public void buildMoveInfo() throws ParseException {


        int logCount= (int) singleLogDao.count(); //数据条数
        int pages=logCount/500;   //总页数

        for (int i = 0; i < pages; i++) {
            Pageable pageable= PageRequest.of(i,500);
            Page<SingleLog> logs=singleLogDao.findAll(pageable);

            for (SingleLog singleLog : logs) {

                String studentId = null;
                String studentName = null;
                String className = null;

                String teacherId = null;
                String teacherName = null;
                String department = null;

                String location = null;
                String locationFrom = null;
                String locationTo = null;
                Date recordTime = null;
                String type = null;
                String campus = null;


                String apName = null;
                String apNameFrom = null;
                String apNameTO = null;
                if (singleLog.getApName() != null) {
                    apName = singleLog.getApName();
                } else {
                    apNameFrom = singleLog.getApNameFrom();
                    apNameTO = singleLog.getApNameTo();
                }

                String userMac = singleLog.getUserMac();
                logger.info("userMac=" + userMac);
                List<ApUser> apUserList = apUserDao.findByMacAddress(userMac);
                ApUser apUser = new ApUser();
                if (apUserList.size() == 1) {
                    apUser = apUserList.get(0);
                } else {
                    logger.info("apUser为空或为公共主机 退出");
                    continue;
                }

                String apUserId = apUser.getUserId();
                int apUserIdLength = 0;
                if (apUserId != null) {
                    apUserIdLength = apUserId.length();
                }

                if (apUserIdLength > 7) { //学生
                    Optional<Student> studentOptional = studentDao.findById(apUserId);
                    Student student = new Student();
                    if (studentOptional.isPresent()) {
                        student = studentOptional.get();
                    } else {
                        logger.info("student 为空 退出");
                        continue;
                    }
                    studentId = student.getStudentNo();
                    studentName = student.getName();
                    className = student.getClassName();
                    if (apName != null) {
                        Ap ap = apDao.findByApName(apName);
                        if (ap == null) {
                            logger.info("ap为null");
                            continue;
                        }
                        String buildingAddress = ((Ap) ap).getBuildingName();
                        String floorId = ap.getFloorId();
                        String roomId = ap.getRoomId();
                        campus = ap.getCampus();
                        if (roomId == null)
                            roomId = "";
                        location = buildingAddress + " " + floorId + " " + roomId;

                    } else {
                        Ap apFrom = apDao.findByApName(apNameFrom);
                        Ap apTo = apDao.findByApName(apNameTO);
                        if (apFrom == null || apTo == null) {
                            logger.info("apFrom或者apTO为null");
                            continue;
                        }
                        String buildingAddressFrom = apFrom.getBuildingName();
                        String roomIdFrom = apFrom.getRoomId();
                        String floorFrom = apFrom.getFloorId();
                        campus = apFrom.getCampus();
                        if (roomIdFrom == null)
                            roomIdFrom = "";
                        locationFrom = buildingAddressFrom + " " + floorFrom + " " + roomIdFrom;

                        String buildingAddressTo = apTo.getBuildingName();
                        String roomIdTo = apTo.getRoomId();
                        if (roomIdTo == null)
                            roomIdTo = "";
                        String floorTO = apTo.getFloorId();
                        locationTo = buildingAddressTo + " " + floorTO + " " + roomIdTo;
                    }


                    recordTime = singleLog.getRecordTime();

                    if (singleLog.getType() == 1)
                        type = "切换";
                    else if (singleLog.getType() == 2)
                        type = "漫游";
                    else if (singleLog.getType() == 3)
                        type = "增加";
                    else
                        type = "删除";


                    MoveInfo moveInfo = new MoveInfo(studentId, studentName, className, location, locationFrom, locationTo, recordTime, type, campus);
                    moveInfoDao.save(moveInfo);
                } else { //教职工
                    Optional<Teacher> teacherOptional = teacherDao.findById(apUserId);
                    Teacher teacher = new Teacher();
                    if (teacherOptional.isPresent()) {
                        teacher = teacherOptional.get();
                    } else {
                        logger.info("teacher 未找到 退出");
                        continue;
                    }
                    teacherId = teacher.getEmployeeId();
                    teacherName = teacher.getName();
                    department = teacher.getDepartment();

                    if (apName != null) {
                        Ap ap = apDao.findByApName(apName);
                        if (ap == null) {
                            logger.info("ap为null");
                            continue;
                        }
                        String buildingAddress = ap.getBuildingName();
                        String floorId = ap.getFloorId();
                        String roomId = ap.getRoomId();
                        campus = ap.getCampus();
                        if (roomId == null)
                            roomId = "";
                        location = buildingAddress + " " + floorId + " " + roomId;

                    } else {
                        Ap apFrom = apDao.findByApName(apNameFrom);
                        Ap apTo = apDao.findByApName(apNameTO);
                        if (apFrom == null || apTo == null) {
                            logger.info("apFrom或者apTO为null");
                            continue;
                        }
                        String buildingAddressFrom = apFrom.getBuildingName();
                        String roomIdFrom = apFrom.getRoomId();
                        String floorFrom = apFrom.getFloorId();
                        campus = apFrom.getCampus();
                        if (roomIdFrom == null)
                            roomIdFrom = "";
                        locationFrom = buildingAddressFrom + " " + floorFrom + " " + roomIdFrom;

                        String buildingAddressTo = apTo.getBuildingName();
                        String roomIdTo = apTo.getRoomId();
                        String floorTO = apTo.getFloorId();
                        if (roomIdTo == null)
                            roomIdTo = "";
                        locationTo = buildingAddressTo + " " + floorTO + " " + roomIdTo;
                    }

                    recordTime = singleLog.getRecordTime();
                    if (singleLog.getType() == 1)
                        type = "切换";
                    else if (singleLog.getType() == 2)
                        type = "漫游";
                    else if (singleLog.getType() == 3)
                        type = "增加";
                    else
                        type = "删除";
                    MoveInfo moveInfo = new MoveInfo(teacherId, teacherName, department, location, locationFrom, locationTo, recordTime, type, campus);
                    moveInfoDao.save(moveInfo);
                }
            }


        }






    }
}
