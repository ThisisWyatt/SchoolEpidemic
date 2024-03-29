package com.smart.go.dao;

import com.smart.go.domain.SingleLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Description 清空原始数据表
 * Author wyatt
 * Date 2020/4/22 14:29
 **/
public interface SingleLogDao extends JpaRepository<SingleLog, Date> {

    @Query(value = "select *  from SchoolEpidemic.aclog_result where record_time >=  ?1", nativeQuery = true)
    List<SingleLog> findAllAfter(Date date);

    @Modifying
    @Transactional
    @Query(value = "truncate  table  SchoolEpidemic.aclog_result", nativeQuery = true)
    void truncateTableAcLogResult();

}
