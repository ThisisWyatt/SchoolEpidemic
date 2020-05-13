package com.smart.go.dao;

import com.smart.go.domain.Ap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApDao extends JpaRepository<Ap, String> {

    Ap findByApName(String apName);

    @Query(value = "select distinct building_name from go.ap_info", nativeQuery = true)
    List<String> getBuildingList();

}
