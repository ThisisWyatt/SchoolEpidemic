package com.smart.go.service.impl;

import com.smart.go.dao.SingleLogDao;
import com.smart.go.domain.SingleLog;
import com.smart.go.service.SingleLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Description  单条日志提取后的数据服务层实现
 * Author Cloudr
 * Date 2020/4/22 14:31
 **/
@Service
public class SingleLogServiceImpl implements SingleLogService {

    @Resource
    private SingleLogDao singleLogDao;

    @Override
    public void save(SingleLog log) {
        singleLogDao.save(log);
    }

}
