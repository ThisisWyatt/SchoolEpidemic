package com.smart.go.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.smart.go.content.GetUserInfoMessage;
import com.smart.go.service.impl.UserInfoServiceImpl;
import com.smart.go.util.ResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Description 获取某个用户的信息
 *
 * @Author wyatt
 * Date 2020/5/16 16:33
 * Version 1.0
 **/
@Controller
@RequestMapping("/getUserInfo")
public class GetUserInfoController {

    @Resource
    private UserInfoServiceImpl userInfoService;

    /**
     * 根据人员id获取其信息
     *
     * @param params 包含id的Json格式的数据
     * @return 包含id、name、department的resultBean
     */
    @RequestMapping("/byId")
    @ResponseBody
    public ResultBean getUserInfoById(@RequestBody String params) {
        GetUserInfoMessage message = JSON.parseObject(params, new TypeReference<GetUserInfoMessage>() {
        });
        String id = message.getId();
        return userInfoService.findUserInfo(id);
    }
}
