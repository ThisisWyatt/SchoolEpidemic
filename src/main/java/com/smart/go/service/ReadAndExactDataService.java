package com.smart.go.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
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
