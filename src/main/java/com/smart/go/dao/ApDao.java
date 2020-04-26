package com.smart.go.dao;

import com.smart.go.domain.Ap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApDao extends JpaRepository<Ap,Long> {

    public Ap findByApName(String apName);
}
