package com.smart.go;

import com.smart.go.content.PathInfoProjection;
import com.smart.go.dao.MoveInfoDao;
import com.smart.go.service.impl.BuildMoveInfoImpl;
import com.smart.go.service.impl.CountPeopleServiceImpl;
import com.smart.go.service.impl.ReadAndExactDataServiceImpl;
import com.smart.go.service.impl.TrackPeopleServiceImpl;
import com.smart.go.util.ResultBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

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

//    @Test
//    void contextLoads() {
//    }

//    @Test
//    void TestCountPoint() throws ParseException {
//
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date time1 = simpleDateFormat.parse("2020-04-13 08:44:26");
//        Date time2 = simpleDateFormat.parse("2020-04-13 11:44:26");
//
//        List<String> moveInfoList = countPeopleService.countInPeriod("2020-04-13 08:44:26", "2020-04-13 11:44:26", "教二楼");
//
//        for (String m : moveInfoList) {
//            System.out.println(m);
//        }
//    }


//    @Test
//    void TestSPoint() throws ParseException {
//        List<String> l = countPeopleService.countAtPoint("2020-04-13 12:44:26", "教二楼");
//        System.out.println(l.size());
//        for (String s : l)
//            System.out.println(s);
//    }

//    @Test
//    void TestReadExtract() throws IOException, ParseException {
//        readAndExactDataService.TestReadLog();
//    }

//    @Test
//    void TestTrack() throws ParseException {
//
//        trackPeopleService.trackSinglePeople("204545", "2020-4-12", "2020-4-15");
//
//    }

//    @Test
//    void TestBuildMoveInfo() {
//        long currentTime = System.currentTimeMillis();
//        buildMoveInfo.buildMoveInfo();
//        logger.info("All  cost: " + (System.currentTimeMillis() - currentTime) + " ms");
//    }

    @Test
    void testProjection() {
        PathInfoProjection p = moveInfoDao.getOneById("105010");
        System.out.println(p.getPeopleId());
        System.out.println(p.getPeopleName());
        System.out.println(p.getDepartment());
    }


}



