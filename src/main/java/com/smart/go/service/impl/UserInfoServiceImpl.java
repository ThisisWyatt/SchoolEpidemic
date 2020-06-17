package com.smart.go.service.impl;

import com.smart.go.content.UserInfo;
import com.smart.go.dao.UserDao;
import com.smart.go.domain.User;
import com.smart.go.service.UserInfoService;
import com.smart.go.util.ResultBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Description 根据Id查询老师或学生的信息
 * Author cloudr
 * Date 2020/5/16 14:54
 * Version 1.0
 **/
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserDao userDao;

    @Override
    // description 根据Id查询老师或学生的信息
    public ResultBean findUserInfo(String id) {

        ResultBean resultBean = new ResultBean();
        List<UserInfo> userInfoList = new LinkedList<>();


        Optional<User> userOptional = userDao.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            userInfoList.add(new UserInfo(user.getUserId(), user.getName(), user.getDepartment()));
            resultBean.setSuccess(true);
            resultBean.setMessage("查询成功");
            resultBean.setDataList(userInfoList);
        } else {
            resultBean.setSuccess(false);
            resultBean.setMessage("查询用户不存在！！！");
        }

        return resultBean;

    }
}
