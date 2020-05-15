package com.smart.go;

import com.smart.go.content.ApInfoProjection;
import com.smart.go.content.CountMessage;
import com.smart.go.content.CountPeopleMessage;
import com.smart.go.dao.ApDao;
import com.smart.go.dao.MoveInfoDao;
import com.smart.go.service.impl.BuildMoveInfoImpl;
import com.smart.go.service.impl.CountPeopleServiceImpl;
import com.smart.go.service.impl.ReadAndExactDataServiceImpl;
import com.smart.go.service.impl.TrackPeopleServiceImpl;
import com.smart.go.util.GPSUtil;
import com.smart.go.util.ResultBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.ParseException;

@SpringBootTest
class GoApplicationTests {

    private Logger logger = LogManager.getLogger(this.getClass());
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

    @Test
    void contextLoads() {
        String str = "教二楼 2 220 ";
        System.out.println(str.substring(0, str.indexOf(" ")));
    }

    @Test
    void GetDataFromLogTest() throws IOException, ParseException {
        readAndExactDataService.TestReadLog();

    }

    @Test
    void BuildMoveInfoTest() throws ParseException {
        buildMoveInfo.buildMoveInfo();
    }

    @Resource
    private ApDao apDao;

    @Test
    void getLatLngTest() {
        ApInfoProjection a = apDao.getLatLngByBName("图书馆会议室");
        System.out.println(a.getLat());
        System.out.println(a.getLng());
    }

    @Test
    void GPSUtilTest() throws ParseException {
        CountMessage message = new CountMessage();
        message.setStartTime("2020-04-22 9:00:00");
        message.setEndTime("2020-04-22 15:00:00");
        String building = "晶体楼";
        ApInfoProjection apInfo = apDao.getLatLngByBName(building);
        CountMessage m = new CountMessage(message.getStartTime(), message.getEndTime(), building);
        int num = countPeopleService.countInPeriod(m).getDataList().size(); //建筑内接入人数
        ApInfoProjection a = apDao.getLatLngByBName(building);
        System.out.println(building);
        CountPeopleMessage c = new CountPeopleMessage(building, num, a.getLat(), a.getLng());
        System.out.println("百度： lat=" + a.getLat() + " lng=" + a.getLng());
        GPSUtil.bd_decryptNum(c);
        System.out.println("高德： lat=" + c.getLat() + " lng=" + c.getLng());
    }


}



