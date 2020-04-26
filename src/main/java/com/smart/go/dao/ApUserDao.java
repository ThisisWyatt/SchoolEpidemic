package com.smart.go.dao;

import com.smart.go.domain.ApUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Jpa21Utils;

import java.util.List;
import java.util.Optional;

/**
 * Description
 * Author Cloudr
 * Date 2020/4/24 2:05
 **/
public interface ApUserDao extends JpaRepository<ApUser,String> {

    List<ApUser> findByMacAddress(String userMac);
}
