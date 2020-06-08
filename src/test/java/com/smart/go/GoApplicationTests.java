package com.smart.go;

import com.smart.go.content.*;
import com.smart.go.dao.ApDao;
import com.smart.go.dao.MoveInfoDao;
import com.smart.go.dao.StudentDao;
import com.smart.go.dao.TeacherDao;
import com.smart.go.domain.SingleLog;
import com.smart.go.domain.Teacher;
import com.smart.go.service.impl.BuildMoveInfoImpl;
import com.smart.go.service.impl.CountPeopleServiceImpl;
import com.smart.go.service.impl.ReadAndExactDataServiceImpl;
import com.smart.go.service.impl.TrackPeopleServiceImpl;
import com.smart.go.util.ExtractData;
import com.smart.go.util.GPSUtil;
import com.smart.go.util.ResultBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.spring5.context.SpringContextUtils;

import javax.annotation.Resource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class GoApplicationTests {

    private final Logger logger = LogManager.getLogger(this.getClass());
    @Resource
    private ReadAndExactDataServiceImpl readAndExactDataService;
    @Resource
    private BuildMoveInfoImpl buildMoveInfo;
    @Resource
    private MoveInfoDao moveInfoDao;
    @Resource
    private CountPeopleServiceImpl countPeopleService;
    @Resource
    private TrackPeopleServiceImpl trackPeopleService;
    @Resource
    private ResultBean resultBean;
    @Resource
    private ApDao apDao;
    @Resource
    private StudentDao studentDao;
    @Resource
    private TeacherDao teacherDao;


    @Test
    void GetDataFromLogTest() throws IOException, ParseException {
        readAndExactDataService.TestReadLog();

    }


    @Test
    void BuildMoveInfoTest() throws ParseException {
        buildMoveInfo.buildMoveInfo();
    }

}



