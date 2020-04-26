package com.smart.go.dao;

import com.smart.go.domain.Ap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApDao extends JpaRepository<Ap,String> {

    public Ap findByApName(String apName);
}
