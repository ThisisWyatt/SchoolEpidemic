package com.smart.go.dao;

import com.smart.go.content.ApInfoProjection;
import com.smart.go.content.ApInfoProjection1;
import com.smart.go.domain.Ap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApDao extends JpaRepository<Ap, String> {

    Ap findByApName(String apName);

    // description 获取所有建筑名
    @Query(value = "select distinct building_name from SchoolEpidemic.ap_info", nativeQuery = true)
    List<String> getBuildingList();

    // description 根据建筑名获取其经纬度
    @Query(value = "select  lat as lat,  lng as lng from SchoolEpidemic.ap_info where building_name=?1 LIMIT 1", nativeQuery = true)
    ApInfoProjection getLatLngByBName(String buildingName);

    // 根据建筑名字查询所有楼层
    @Query(value = "SELECT DISTINCT(floor_id) FROM SchoolEpidemic.ap_info WHERE building_name=?1 ORDER BY floor_id", nativeQuery = true)
    List<String> getLayers(String buildingName);

    // 根据建筑名字，楼层 查询所有房间
    @Query(value = "SELECT DISTINCT(room_id) FROM SchoolEpidemic.ap_info WHERE (building_name = ?1 and floor_id=?2)  ORDER BY room_id", nativeQuery = true)
    List<String> getRooms(String buildingName, String layers);

    // 根据ap名字获取它的位置和校区信息
    @Query(value = "select location as location,campus as campus from schoolepidemic.ap_info where ap_info.ap_name=?1 limit 1", nativeQuery = true)
    ApInfoProjection1 getLocationAndCampus(String apName);

}
