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

    List<ApUser> findByMacAddress(String userMac);

    @Query(value = "select user_id from SchoolEpidemic.user_info where mac_address=?1", nativeQuery = true)
    List<String> findIdByMacAddress(String userMac);

    @Query(value = "select * from schoolepidemic.user_info where MAC_ADDRESS=?1", nativeQuery = true)
    List<ApUser> findByMacAddress1(String macAddress);

    @Transactional
    @Modifying
    @Query(value = "DELETE from schoolepidemic.user_info where MAC_ADDRESS=?1", nativeQuery = true)
    void delete(String macAddress);
}
