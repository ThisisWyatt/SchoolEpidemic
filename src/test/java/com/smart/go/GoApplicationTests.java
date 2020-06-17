package com.smart.go;

import com.smart.go.content.ApInfoProjection1;
import com.smart.go.dao.ApDao;
import com.smart.go.dao.ApUserDao;
import com.smart.go.dao.MoveInfoDao;
import com.smart.go.domain.ApUser;
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

    @Resource
    private ApUserDao apUserDao;

    @Test
    void getGoodUserInfo() {

        List<ApUser> apUserList = apUserDao.findAll();
        for (ApUser apUser : apUserList) {
            List<ApUser> apUserList1 = apUserDao.findByMacAddress1(apUser.getMacAddress());
            if (apUserList1.size() > 1)
                apUserDao.delete(apUser.getMacAddress());
        }
    }

}



