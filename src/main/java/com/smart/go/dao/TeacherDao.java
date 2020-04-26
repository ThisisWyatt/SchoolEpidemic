package com.smart.go.dao;

import com.smart.go.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Description
 * Author Cloudr
 * Date 2020/4/24 2:38
 **/
public interface TeacherDao extends JpaRepository<Teacher,String> {
}
