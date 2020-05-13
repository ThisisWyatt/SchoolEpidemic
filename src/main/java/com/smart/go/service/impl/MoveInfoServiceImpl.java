package com.smart.go.service.impl;

import com.smart.go.content.PathInfoProjection;
import com.smart.go.dao.MoveInfoDao;
import com.smart.go.domain.MoveInfo;
import com.smart.go.service.MoveInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Description
 * Author cloudr 人员移动信息服务层实现
 * Date 2020/5/3 23:15
 * Version 1.0
 **/
@Service
public class MoveInfoServiceImpl implements MoveInfoService {

    @Resource
    private MoveInfoDao moveInfoDao;

    @Override
    // description 查询在一段时间内某个建筑的所有新接入、断开、切换出、切换入的所有用户
    public List<String> count1(String location, Date startTime, Date endTime) {
        return moveInfoDao.count1(location, startTime, endTime);
    }

    @Override
    // description 查询在一段时间内某个建筑的所有新接入、断开、切换出、切换入的所有用户
    public List<String> count2(String location, Date startTime, Date endTime) {
        return moveInfoDao.count2(location, startTime, endTime);
    }

    @Override
    // description  查询用户离当前时间最近的一次Ap连接信息
    public MoveInfo sPoint(String peopleId, Date startTime, Date endTime) {
        return moveInfoDao.sPoint(peopleId, startTime, endTime);
    }

    @Override
    // description 根据Id查询出基本信息
    public PathInfoProjection getOneById(String Id) {
        return moveInfoDao.getOneById(Id);
    }


}
