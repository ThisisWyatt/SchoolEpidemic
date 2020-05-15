package com.smart.go.dao;

import com.smart.go.content.ApInfoProjection;
import com.smart.go.domain.Ap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApDao extends JpaRepository<Ap, String> {

    Ap findByApName(String apName);

    @Query(value = "select distinct building_name from go.ap_info", nativeQuery = true)
    List<String> getBuildingList();

    @Query(value = "select  lat as lat,  lng as lng from go.ap_info where building_name=?1 LIMIT 1", nativeQuery = true)
    ApInfoProjection getLatLngByBName(String buildingName);

}
