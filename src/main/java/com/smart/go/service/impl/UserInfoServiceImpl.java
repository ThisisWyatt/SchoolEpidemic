package com.smart.go.service.impl;

import com.smart.go.content.StudentProjection;
import com.smart.go.content.UserInfo;
import com.smart.go.dao.StudentDao;
import com.smart.go.dao.TeacherDao;
import com.smart.go.domain.Teacher;
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
    private StudentDao studentDao;
    @Resource
    private TeacherDao teacherDao;

    @Override
    // description 根据Id查询老师或学生的信息
    public ResultBean findUserInfo(String id) {
        ResultBean resultBean = new ResultBean();
        List<UserInfo> userInfoList = new LinkedList<>();
        StudentProjection student = null;
        Teacher teacher = null;
        boolean judge;
        if (id.length() > 7) {
            Optional<StudentProjection> studentOptional = studentDao.findThroughId(id);
            if (studentOptional.isPresent()) {
                student = studentOptional.get();
                judge = true;
            }
        } else {
            Optional<Teacher> teacherOptional = teacherDao.findByEmployeeId(id);
            if (teacherOptional.isPresent()) {
                teacher = teacherOptional.get();
                judge = true;
            }
        }
        if (student != null) {
            UserInfo userInfo = new UserInfo(student.getStudentNo(), student.getName(), student.getClassName());
            userInfoList.add(userInfo);
            resultBean.setSuccess(true);
            resultBean.setMessage("查询成功");
            resultBean.setDataList(userInfoList);
        } else if (teacher != null) {
            UserInfo userInfo = new UserInfo(teacher.getEmployeeId(), teacher.getName(), teacher.getDepartment());
            userInfoList.add(userInfo);
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
