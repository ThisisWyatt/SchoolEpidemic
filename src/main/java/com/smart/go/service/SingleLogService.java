package com.smart.go.service;

import com.smart.go.domain.SingleLog;
import org.springframework.stereotype.Service;

/**
 * Description 单条日志提取后的数据服务层
 * Author Cloudr
 * Date 2020/4/22 14:30
 **/
@Service
public interface SingleLogService {

    void save(SingleLog Log);

}
