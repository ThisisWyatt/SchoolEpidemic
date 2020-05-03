package com.smart.go.dao;

import com.smart.go.domain.ApUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Description
 * Author Cloudr
 * Date 2020/4/24 2:05
 **/
public interface ApUserDao extends JpaRepository<ApUser, String> {

    List<ApUser> findByMacAddress(String userMac);
}
