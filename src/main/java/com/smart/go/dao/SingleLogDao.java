package com.smart.go.dao;

import com.smart.go.domain.SingleLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

/**
 * Description
 * Author Cloudr
 * Date 2020/4/22 14:29
 **/
public interface SingleLogDao extends JpaRepository<SingleLog, Date> {

}
