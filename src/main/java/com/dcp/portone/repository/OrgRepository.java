package com.dcp.portone.repository;

import com.dcp.portone.entity.Org;
import com.dcp.portone.model.OrgnizationModel;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.print.DocFlavor;
import java.util.Set;

@Repository
public interface OrgRepository extends JpaRepository<Org, Long> {
/*    Set<Org> findByNameContaining(String name);
    Set<Org> findByLocationContaining(String location);
    Org getById(Long Id);*/

}
