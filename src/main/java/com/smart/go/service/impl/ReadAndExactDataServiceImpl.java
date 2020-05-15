package com.smart.go.service.impl;

import com.smart.go.domain.SingleLog;
import com.smart.go.service.ReadAndExactDataService;
import com.smart.go.service.SingleLogService;
import com.smart.go.util.ExtractData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;

/**
 * Description
 * Author cloudr
 * Date 2020/5/5 21:40
 * Version 1.0
 **/
@Service
public class ReadAndExactDataServiceImpl implements ReadAndExactDataService {

    @Resource
    private ExtractData extractData;
    @Resource
    private SingleLogService singleLogService;

    private Logger logger = LogManager.getLogger(this.getClass());

    public void TestReadLog() throws IOException, ParseException {
        InputStream stream = getClass().getClassLoader().getResourceAsStream("messages-20200426");
        assert stream != null;
        BufferedReader br = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
        while (br.readLine() != null) {
            String messages = br.readLine();
            if (messages != null) {
                SingleLog singleLog = extractData.dispose(messages);
                if (singleLog != null) {
                    singleLogService.save(singleLog);
                }
            } else {
                logger.info("此条记录为空");
            }
        }
        br.close();
    }

}
