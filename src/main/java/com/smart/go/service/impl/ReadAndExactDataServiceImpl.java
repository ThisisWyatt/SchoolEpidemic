package com.smart.go.service.impl;

import com.smart.go.domain.SingleLog;
import com.smart.go.service.ReadAndExactDataService;
import com.smart.go.service.SingleLogService;
import com.smart.go.util.ExtractData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description 从日志中读取数据，处理并存入数据库
 * Author wyatt
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

            // -86400000L 是为得到前一天的日期(处理的数据都是前一天的数据)
            Date date = new Date(new Date().getTime() - 86400000L);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(date);

            //从日志中读取逐行读取数据进行处理
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
                        if (singleLog.getApName() != null) {
                            //排除宿舍区的信息，此系统不需要采集宿舍区的信息
                            if (!singleLog.getApName().startsWith("X") && !singleLog.getApName().startsWith("L") && !singleLog.getApName().startsWith("QJ")) {
                                singleLogService.save(singleLog);
                            }
                        } else {
                            if ((!singleLog.getApNameFrom().startsWith("X") && !singleLog.getApNameFrom().startsWith("L") && !singleLog.getApNameFrom().startsWith("QJ")) && (!singleLog.getApNameTo().startsWith("X") && !singleLog.getApNameTo().startsWith("L") && !singleLog.getApNameTo().startsWith("QJ"))) {
                                singleLogService.save(singleLog);
                            }
                        }
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
