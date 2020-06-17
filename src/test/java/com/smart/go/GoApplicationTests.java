package com.smart.go;

import com.smart.go.dao.ApDao;
import com.smart.go.dao.ApUserDao;
import com.smart.go.dao.MoveInfoDao;
import com.smart.go.service.impl.BuildMoveInfoServiceImpl;
import com.smart.go.util.ExtractData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class GoApplicationTests {

    private final Logger logger = LogManager.getLogger(this.getClass());

//    @Test
//    void testTime(){
//        Date dateYesterday0 = new Date(new Date().getTime() - 86400000L);
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        Date dateYesterday=new Date();
//        try {
//            String dateYesterday1=formatter.format(dateYesterday0);
//            dateYesterday=formatter.parse(dateYesterday1);
//            System.out.println(dateYesterday);
//        }catch (ParseException e){
//            e.printStackTrace();
//            logger.error("格式化当前时间日期格式错误");
//        }
//    }
}



