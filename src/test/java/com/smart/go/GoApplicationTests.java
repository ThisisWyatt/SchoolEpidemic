package com.smart.go;

import com.smart.go.dao.ApDao;
import com.smart.go.dao.MoveInfoDao;
import com.smart.go.dao.StudentDao;
import com.smart.go.dao.TeacherDao;
import com.smart.go.domain.SingleLog;
import com.smart.go.service.impl.BuildMoveInfoServiceImpl;
import com.smart.go.service.impl.CountPeopleServiceImpl;
import com.smart.go.service.impl.ReadAndExactDataServiceImpl;
import com.smart.go.service.impl.TrackPeopleServiceImpl;
import com.smart.go.util.ExtractData;
import com.smart.go.util.ResultBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.sound.midi.Soundbank;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class GoApplicationTests {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Resource
    private ExtractData extractData;

//    @Test
//    void testDate() throws ParseException {
//        String s="Jun  9 00:01:33 172.16.0.30  14924685: *Jun  8 23:59:59: %APMG-6-STA_DEL: Client(149d.094f.2997) notify: leave AP (X4#SS-422-AM5528-08).#015";
//        SingleLog singleLog=extractData.dispose(s);
//        System.out.println(singleLog);
//    }


}



