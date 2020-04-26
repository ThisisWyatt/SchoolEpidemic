package com.smart.go.service;

import com.smart.go.domain.SingleLog;
import com.smart.go.util.ExtractData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;

/**
 * Description  从日志文件中读取数据并解析
 * Author Cloudr
 * Date 2020/4/22 12:54
 **/
@Service
public class ReadAndExactDataService {
    @Resource
    private ExtractData extractData;
    @Resource
    private SingleLogService singleLogService;

    public void TestReadLog() throws IOException, ParseException {
        InputStream stream = getClass().getClassLoader().getResourceAsStream("messages-20200419");
        assert stream != null;
        BufferedReader br = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
        while (br.readLine() != null) {
            String messages = br.readLine();
            if(messages!=null){
                SingleLog singleLog=extractData.dispose(messages);
                if(singleLog!=null){
                    singleLogService.save(singleLog);
                }
            }
        }
        br.close();
    }

    public static void main(String[] args) throws IOException, ParseException {
        ReadAndExactDataService r=new ReadAndExactDataService();
        r.TestReadLog();
    }
}
