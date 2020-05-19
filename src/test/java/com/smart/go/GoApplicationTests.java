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

    @Resource
    private ExtractData extractData;

    @Test
    void TestExactError() {

        String s = "May  3 03:39:52 172.16.0.10  22311546: *May  3 03:40:02: XAUT-JH-Wlan-N18K-Slot1/8&2/8-VAC %APMG-6-STA_CHANGE: Client(48e2.4427.efda) move from AP(基建处与武装部4号 AP5280) radio 7 to AP(基建处与武装部4号 AP5280) radio 5.";
        String ss = "Apr 28 07:42:05 172.16.0.10  21291621: *Apr 28 07:42:09: XAUT-JH-Wlan-N18K-Slot1/8&2/8-VAC %APMG-6-STA_CHANGE: (*2) Client(3094.3595.ea69) move from AP(南体育馆北面靠西室外AP630) radio 2 to AP(教六楼北楼北面室外 AP630) radio 2.";
        SingleLog singleLog = new SingleLog();
        try {
            String reg = "Client\\(([a-z0-9.]{1,})\\) move from AP\\((.{1,})\\) radio [0-9]{1,} to AP\\((.{1,})\\) radio [0-9]{1,}.";
            String[] messageInfo = s.split(Content.USER_STACHANGE + ": ");
            String[] dateInfo0 = messageInfo[0].split(" ");
            Calendar now = Calendar.getInstance();
            String recordStr = now.get(Calendar.YEAR) + "-" + dateInfo0[0].trim() + "-" + dateInfo0[1].trim() + " " + dateInfo0[2].trim();
            for (int i = 0; i < dateInfo0.length; i++) {
                System.out.println("第" + i + "个 " + dateInfo0[i]);
            }

            System.out.println();
            System.out.println(recordStr);
            singleLog = extractData.dispose(s);
        } catch (Exception e) {
            System.out.println("时间解析错误");
        }
        System.out.println(singleLog);
    }

}



