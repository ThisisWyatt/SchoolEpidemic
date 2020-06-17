package com.smart.go;

import com.smart.go.content.ApInfoProjection1;
import com.smart.go.dao.ApDao;
import com.smart.go.dao.MoveInfoDao;
import com.smart.go.domain.MoveInfo;
import com.smart.go.service.impl.BuildMoveInfoServiceImpl;
import com.smart.go.util.ExtractData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

@SpringBootTest
class GoApplicationTests {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Resource
    private ExtractData extractData;

    @Resource
    private MoveInfoDao moveInfoDao;

    @Resource
    private BuildMoveInfoServiceImpl buildMoveInfoService;

    @Resource
    private ApDao apDao;

    @Test
    void getLocationAndCampusByApName() {
        try {

            buildMoveInfoService.buildMoveInfo2();
        } catch (ParseException e) {
            System.out.println("时间解析错误");
        }

    }

}



