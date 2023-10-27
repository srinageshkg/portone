package com.dcp.portone.repository;

import com.dcp.portone.entity.Emp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpRepository extends JpaRepository<Emp, Long> {
    public List<Emp> findByEmpNameContaining(String name);
}
