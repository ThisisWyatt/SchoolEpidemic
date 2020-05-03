package com.smart.go.service.impl;

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
    public List<MoveInfo> count(String location, String location1, String location2, Date startTime, Date endTime) {
        return moveInfoDao.findByLocationLikeOrLocationFromLikeOrLocationToLikeAndRecordTimeAfterAndRecordTimeBefore(location, location1, location2, startTime, endTime);
    }


}
