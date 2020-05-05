package com.smart.go.dao;

import com.smart.go.domain.MoveInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * Description 人员移动信息服务层
 * Author Cloudr
 * Date 2020/4/24 2:25
 **/
public interface MoveInfoDao extends JpaRepository<MoveInfo,String> {

    // description 测试使用原生SQL
    @Query(value = "select * from go.move_info where move_info.people_id =?1", nativeQuery = true)
    List<MoveInfo> mList(String peopleId);

    // description 查询在一段时间内某个建筑的所有新接入、断开、切换出、切换入的所有用户Id select * from move_info where location like ‘location’ Or locationFrom like 'location' Or locationTo like ‘location’ and recordTime < endTime and recordTime > time
    @Query(value = "SELECT distinct  people_id from go.move_info where (location like ?1 or  location_from like ?1 or location_to like ?1) and (record_time BETWEEN ?2 and ?3 );", nativeQuery = true)
    List<String> count1(String location, Date startTime, Date endTime);

    // description 查询当天凌晨0点到当天某一时间点某个地点所有 接入、删除、切换入的所有用户Id  select * from move_info where location like ‘location’ Or locationTo like ‘location’ and recordTime < endTime and recordTime > time
    @Query(value = "select distinct people_id from go.move_info where (location like ?1 or location_to like ?1 ) and (record_time BETWEEN ?2 and ?3 );", nativeQuery = true)
    List<String> count2(String location, Date startTime, Date endTime);

    // description 离当前时间最近的一次Ap连接信息
    @Query(value = "SELECT * FROM go.move_info where ( people_id=?1 and record_time< ?3 and record_time > ?2 ) ORDER BY ?3 -record_time ASC limit 1 ;", nativeQuery = true)
    MoveInfo sPoint(String peopleId, Date startTime, Date endTime);

    // description 根据人员Id查询在某个时间段的ap连接信息
    List<MoveInfo> getByPeopleIdAndRecordTimeBetween(String peopleId, Date startTime, Date endTime);

}
