package com.smart.go.dao;

import com.smart.go.content.StudentProjection;
import com.smart.go.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * Description
 * Author Cloudr
 * Date 2020/4/24 2:01
 **/
public interface StudentDao extends JpaRepository<Student, String> {

    @Query(value = "select student_no as studentNo,name as name ,class_name as className from go.student_info where student_no=?1", nativeQuery = true)
    Optional<StudentProjection> findThroughId(String id);

}
