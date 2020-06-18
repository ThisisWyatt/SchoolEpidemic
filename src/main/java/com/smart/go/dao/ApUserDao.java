package com.smart.go.dao;

import com.smart.go.domain.ApUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Description
 * Author Cloudr
 * Date 2020/4/24 2:05
 **/
public interface ApUserDao extends JpaRepository<ApUser, String> {

}
