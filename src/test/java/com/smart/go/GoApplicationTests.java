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
    void testReturn() {
        System.out.println("hello");
        System.out.println("hello");
        int a = (int) (Math.random() * 10);
        if (a > 5)
            return;
        System.out.println("hello");
    }

}



