package com.smart.go.service.impl;

import com.smart.go.domain.SingleLog;
import com.smart.go.service.ReadAndExactDataService;
import com.smart.go.service.SingleLogService;
import com.smart.go.util.ExtractData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description
 * Author cloudr
 * Date 2020/5/5 21:40
 * Version 1.0
 **/
@Service
@EnableScheduling
public class ReadAndExactDataServiceImpl implements ReadAndExactDataService {

    private final Logger logger = LogManager.getLogger(this.getClass());
    @Resource
    private ExtractData extractData;
    @Resource
    private SingleLogService singleLogService;

    public void TestReadLog() {
        try {

            Date date = new Date(new Date().getTime() - 86400000L);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(date);

//            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("C:/Users/life/Desktop/"+dateString+".log")), StandardCharsets.UTF_8));
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("/root/SchoolEpidemic/files/logFiles/" + dateString + ".log")), StandardCharsets.UTF_8));
            while (br.readLine() != null) {
                String messages = br.readLine();
                if (messages != null) {
                    SingleLog singleLog = null;
                    try {
                        singleLog = extractData.dispose(messages);
                    } catch (ParseException parseE) {
                        logger.error("此条记录中日期信息错误 " + messages);
                        parseE.printStackTrace();
                    }

                    if (singleLog != null) {
                        singleLogService.save(singleLog);
                    }
                } else {
                    logger.info("此条记录为空");
                }
            }
            br.close();
        } catch (IOException ioE) {
            logger.error("读取日志源文件出错！！！ ");
            ioE.printStackTrace();
        }
    }

}
