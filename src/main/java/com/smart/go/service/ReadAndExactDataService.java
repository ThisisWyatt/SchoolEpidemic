package com.smart.go.service;

import com.smart.go.domain.SingleLog;
import com.smart.go.util.ExtractData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
public interface ReadAndExactDataService {

    void TestReadLog() throws IOException, ParseException;

}
