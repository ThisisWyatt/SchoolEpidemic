package com.smart.go.dao;

import com.smart.go.content.PathInfoProjection;
import com.smart.go.domain.MoveInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Description 人员移动信息服务层
 * Author Cloudr
 * Date 2020/4/24 2:25
 **/
public interface MoveInfoDao extends JpaRepository<MoveInfo, String> {

    // description 测试使用原生SQL
    @Query(value = "select * from SchoolEpidemic.move_info where move_info.people_id =?1", nativeQuery = true)
    List<MoveInfo> mList(String peopleId);

    // description 查询在一段时间内某个建筑的所有新接入、断开、切换出、切换入的所有用户Id select * from move_info where location like ‘location’ Or locationFrom like 'location' Or locationTo like ‘location’ and recordTime < endTime and recordTime > time
    @Query(value = "select distinct  people_id from SchoolEpidemic.move_info where (location like ?1 or  location_from like ?1 or location_to like ?1) and (record_time BETWEEN ?2 and ?3 );", nativeQuery = true)
    List<String> count1(String location, Date startTime, Date endTime);

    // description 查询当天凌晨0点到当天某一时间点某个地点所有 接入、删除、切换入的所有用户Id  select * from move_info where location like ‘location’ Or locationTo like ‘location’ and recordTime < endTime and recordTime > time
    @Query(value = "select distinct people_id from SchoolEpidemic.move_info where (location like ?1 or location_to like ?1 ) and (record_time BETWEEN ?2 and ?3 );", nativeQuery = true)
    List<String> count2(String location, Date startTime, Date endTime);

    // description 离当前时间最近的一次Ap连接信息
    @Query(value = "SELECT * FROM SchoolEpidemic.move_info where ( people_id=?1 and record_time< ?3 and record_time > ?2 ) ORDER BY ?3 -record_time ASC limit 1 ;", nativeQuery = true)
    MoveInfo sPoint(String peopleId, Date startTime, Date endTime);

    // description 根据人员Id查询在某个时间段的ap连接信息
    List<MoveInfo> getByPeopleIdAndRecordTimeBetween(String peopleId, Date startTime, Date endTime);

    //根据Id查询出基本信息
    @Query(value = "SELECT distinct people_id as peopleId,name as peopleName,department as department FROM SchoolEpidemic.move_info where people_id=?1  ;", nativeQuery = true)
    PathInfoProjection getOneById(String id);

    //关联增加、删除的情况
    @Transactional
    @Modifying
//    @Query(value = "insert into SchoolEpidemic.move_info (record_time,people_id,name,department,ap_type,ap_name) select aclog_result.record_time,student_info.student_no,student_info.name,student_info.college_name,aclog_result.type,aclog_result.ap_name from SchoolEpidemic.aclog_result,SchoolEpidemic.student_info,SchoolEpidemic.user_info where (aclog_result.record_time  BETWEEN '2020-06-08 00:00:00' and '2020-06-08 00:20:00') and (aclog_result.user_mac=user_info.MAC_ADDRESS ) and (user_info.USER_ID=student_info.student_no) and (aclog_result.ap_name is not null)", nativeQuery = true)
    @Query(value = "insert into SchoolEpidemic.move_info (record_time,people_id,name,department,ap_type,ap_name) select aclog_result.record_time,user_info.user_id,user_info.name,user_info.department,aclog_result.type,aclog_result.ap_name from SchoolEpidemic.aclog_result,SchoolEpidemic.user_info,SchoolEpidemic.ap_user_info where (aclog_result.record_time  BETWEEN '2020-06-08 00:00:00' and '2020-06-08 00:20:00') and (aclog_result.user_mac=ap_user_info.MAC_ADDRESS ) and (ap_user_info.USER_ID=user_info.user_id) and (aclog_result.ap_name is not null)", nativeQuery = true)
    void buildMoveInfo1();

    //关联切换、漫游的情况
    @Transactional
    @Modifying
//    @Query(value = "insert into SchoolEpidemic.move_info (record_time,people_id,name,department,ap_type,ap_name_from,ap_name_to) select aclog_result.record_time,student_info.student_no,student_info.name,student_info.college_name,aclog_result.type,aclog_result.ap_name_from,aclog_result.ap_name_to from  SchoolEpidemic.aclog_result,SchoolEpidemic.student_info,SchoolEpidemic.user_info where (aclog_result.record_time  BETWEEN '2020-06-08 00:00:00' and '2020-06-08 00:20:00') and (aclog_result.user_mac=user_info.MAC_ADDRESS ) and (user_info.USER_ID=student_info.student_no) and (aclog_result.ap_name is  null)", nativeQuery = true)
    @Query(value = "insert into SchoolEpidemic.move_info (record_time,people_id,name,department,ap_type,ap_name_from,ap_name_to) select aclog_result.record_time,user_info.user_id,user_info.name,user_info.department,aclog_result.type,aclog_result.ap_name_from,aclog_result.ap_name_to from  SchoolEpidemic.aclog_result,SchoolEpidemic.user_info,SchoolEpidemic.ap_user_info where (aclog_result.record_time  BETWEEN '2020-06-08 00:00:00' and '2020-06-08 00:20:00') and (aclog_result.user_mac=ap_user_info.MAC_ADDRESS ) and (ap_user_info.USER_ID=user_info.user_id) and (aclog_result.ap_name is  null)", nativeQuery = true)
    void buildMoveInfo2();

    @Query(value = "select *  from SchoolEpidemic.move_info where record_time >=  ?1", nativeQuery = true)
    List<MoveInfo> findAllAfter(Date date);
}
