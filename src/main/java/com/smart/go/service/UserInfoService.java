package com.smart.go.service;

import com.smart.go.util.ResultBean;
import org.springframework.stereotype.Service;

/**
 * Description 根据Id查询老师或学生的信息
 * Author cloudr
 * Date 2020/5/16 14:52
 * Version 1.0
 **/
@Service
public interface UserInfoService {

    // description 根据Id查询老师或学生的信息
    ResultBean findUserInfo(String id);
}
