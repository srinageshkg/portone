package com.dcp.portone.repository;

import com.dcp.portone.entity.Emp;
import com.dcp.portone.entity.Org;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

@SpringBootTest
public class OrgRepositoryTest {

    @Autowired
    private OrgRepository orgRepository;

    @Test
    public void saveOrg() {
        Org org = new Org();
        //org.setId(1001L);
        org.setName("Google");
        org.setLocation("TX");

        Emp emp1 = new Emp();
        //emp1.setId(203L);
        emp1.setEmpName("Alex Ga");
        emp1.setOrg(org);

        Emp emp2 = new Emp();
        //emp2.setId(204L);
        emp2.setEmpName("Peter pan");
        emp2.setOrg(org);

        Emp emp3 = new Emp();
        //emp2.setId(204L);
        emp3.setEmpName("John ter");
        emp3.setOrg(org);

        org.setEmps(Set.of(emp1, emp2, emp3));

        orgRepository.save(org);
    }

}
