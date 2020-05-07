package com.smart.go;

import com.smart.go.content.PathInfo;
import com.smart.go.dao.ApDao;
import com.smart.go.dao.MoveInfoDao;
import com.smart.go.dao.SingleLogDao;
import com.smart.go.domain.Ap;
import com.smart.go.domain.MoveInfo;
import com.smart.go.domain.SingleLog;
import com.smart.go.domain.Teacher;
import com.smart.go.service.BuildMoveInfo;
import com.smart.go.service.ReadAndExactDataService;
import com.smart.go.service.impl.BuildMoveInfoImpl;
import com.smart.go.service.impl.CountPeopleServiceImpl;
import com.smart.go.service.impl.ReadAndExactDataServiceImpl;
import com.smart.go.service.impl.TrackPeopleServiceImpl;
import com.smart.go.util.ResultBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.discovery.ClasspathResourceSelector;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.support.MethodOverride;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import sun.reflect.generics.tree.VoidDescriptor;

import javax.annotation.Resource;
import javax.persistence.Table;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.Signature;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class GoApplicationTests {

    @Test
    void contextLoads() {
    }

    private Logger logger = LogManager.getLogger(this.getClass());

    @Resource
    private ReadAndExactDataServiceImpl readAndExactDataService;

    @Test
    void TestReadExtract() throws IOException, ParseException {
        readAndExactDataService.TestReadLog();
    }

    @Resource
    private BuildMoveInfoImpl buildMoveInfo;

    @Test
    void TestBuildMoveInfo() {
        long currentTime = System.currentTimeMillis();
        buildMoveInfo.buildMoveInfo();
        logger.info("All  cost: " + (System.currentTimeMillis() - currentTime) + " ms");
    }

    @Test
    void Read() throws IOException {

        InputStream stream = getClass().getClassLoader().getResourceAsStream("test.log");
        assert stream != null;
        BufferedReader br = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
        System.out.println(br.readLine());

    }

    @Resource
    private MoveInfoDao moveInfoDao;
    @Resource
    private CountPeopleServiceImpl countPeopleService;

    @Test
    void TestCountPoint() throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time1 = simpleDateFormat.parse("2020-04-13 08:44:26");
        Date time2 = simpleDateFormat.parse("2020-04-13 11:44:26");

        List<String> moveInfoList = countPeopleService.countInPeriod("2020-04-13 08:44:26", "2020-04-13 11:44:26", "教二楼");

        for (String m : moveInfoList) {
            System.out.println(m);
        }
    }


    @Test
    void TestSPoint() throws ParseException {
        List<String> l = countPeopleService.countAtPoint("2020-04-13 12:44:26", "教二楼");
        System.out.println(l.size());
        for (String s : l)
            System.out.println(s);
    }


    @Resource
    private TrackPeopleServiceImpl trackPeopleService;

    @Test
    void TestTrack() throws ParseException {

        trackPeopleService.trackSinglePeople("204545", "2020-4-12", "2020-4-15");

    }

    @Resource
    private ResultBean resultBean;

    @Test
    void TestJson() throws ParseException {

        List<PathInfo> list = trackPeopleService.trackSinglePeople("204545", "2020-4-12", "2020-4-15");

    }



}



