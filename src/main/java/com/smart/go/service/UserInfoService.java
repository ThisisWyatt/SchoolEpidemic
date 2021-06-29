package com.smart.go.service;

import com.smart.go.util.ResultBean;
import org.springframework.stereotype.Service;

/**
 * Description 根据Id查询老师或学生的信息
 *
 * @Author wyatt
 * Date 2020/5/16 14:52
 * Version 1.0
 **/
@Service
public interface UserInfoService {

    /**
     * 根据Id查询老师或学生的信息
     *
     * @param id 老师或者学生的信息
     * @return 包含id、name、department的resultBean
     */
    ResultBean findUserInfo(String id);
}
