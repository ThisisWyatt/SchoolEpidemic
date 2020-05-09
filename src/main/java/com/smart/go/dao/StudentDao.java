package com.smart.go.dao;

import com.smart.go.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Description
 * Author Cloudr
 * Date 2020/4/24 2:01
 **/
public interface StudentDao extends JpaRepository<Student, String> {
}
