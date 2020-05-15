package com.smart.go.dao;

import com.smart.go.domain.SingleLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * Description
 * Author Cloudr
 * Date 2020/4/22 14:29
 **/
public interface SingleLogDao extends JpaRepository<SingleLog, Date> {

    @Query(value = "select *  from go.aclog_result where record_time >=  ?1", nativeQuery = true)
    List<SingleLog> findAllAfter(Date date);
}
