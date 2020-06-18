package com.smart.go;

import com.smart.go.dao.MoveInfoDao;
import com.smart.go.domain.MoveInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
class GoApplicationTests {

    private final Logger logger = LogManager.getLogger(this.getClass());
    @Resource
    private MoveInfoDao moveInfoDao;

    @Test
    void testTime() throws ParseException {

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startTime = sdf1.parse("2020-06-17 00:00:00");
        Date endTime = sdf1.parse("2020-06-17 15:00:00");
        List<MoveInfo> moveInfoList = moveInfoDao.findInPeriod("2180120050", startTime, endTime);
        System.out.println(moveInfoList.size());

    }
}



