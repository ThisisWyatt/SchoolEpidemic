package com.smart.go.dao;

import com.smart.go.domain.MoveInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Description 人员移动信息服务层
 * Author Cloudr
 * Date 2020/4/24 2:25
 **/
public interface MoveInfoDao extends JpaRepository<MoveInfo,String> {

    // description 查询在一段时间内某个建筑的所有新接入、断开、切换出、切换入的所有用户
    List<MoveInfo> findByLocationLikeOrLocationFromLikeOrLocationToLikeAndRecordTimeAfterAndRecordTimeBefore(String location,String location1,String location2,Date startTime,Date endTime);

}
