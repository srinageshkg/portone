package com.dcp.portone.service;

import com.dcp.portone.entity.Emp;
import com.dcp.portone.entity.Org;
import com.dcp.portone.model.EmployeeModel;
import com.dcp.portone.model.OrgRequestModel;
import com.dcp.portone.model.OrgnizationModel;
import com.dcp.portone.repository.OrgRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Builder
@Service
public class OrgServiceImpl implements OrgService {
    @Autowired
    private OrgRepository orgRepository;
    @Override
    public List<OrgnizationModel> getOrgs() {
        List<OrgnizationModel> orgnizationModels = new ArrayList<>();

        for (Org org : orgRepository.findAll()) {
            OrgnizationModel orgnizationModel = new OrgnizationModel();
            orgnizationModel.setOrgId(org.getId());
            orgnizationModel.setOrgName(org.getName());
            orgnizationModel.setLocation(org.getLocation());

            Set<EmployeeModel> eps = new HashSet<>();
            org.getEmps().forEach(e -> {
                EmployeeModel em = new EmployeeModel();
                em.setEmpId(e.getId());
                em.setEmpName(e.getEmpName());
                eps.add(em);
                System.out.println("Emp Name: " + em);
            });
            orgnizationModel.setEmps(eps);
            orgnizationModels.add(orgnizationModel);
        }
        return orgnizationModels;
    }
    @Override
    public Org saveOrganization(OrgnizationModel orgModel) {

        Org org = new Org();
        org.setId(orgModel.getOrgId());
        org.setName(orgModel.getOrgName());
        org.setLocation(orgModel.getOrgName());
        //org.setEmps(orgModel.getEmps());

        return orgRepository.save(org);
    }
    @Override
    public OrgnizationModel getOrgById(Long id){
        OrgnizationModel orgnizationModel = new OrgnizationModel();

        Org org = orgRepository.getReferenceById(id);
        orgnizationModel.setOrgId(org.getId());
        orgnizationModel.setOrgName(org.getName());
        orgnizationModel.setLocation(org.getLocation());

        return orgnizationModel;
    }
    @Override
    public Org updateOrg(OrgnizationModel orgModel) {

        Optional<Org> retrieveOrg = Optional.of(orgRepository.findById(orgModel.getOrgId()).get());

        if (Objects.nonNull(orgModel.getOrgName()) && !"".equalsIgnoreCase(orgModel.getOrgName())) {
            retrieveOrg.get().setName(orgModel.getOrgName());
        }
        retrieveOrg.get().setId(orgModel.getOrgId());
        retrieveOrg.get().setLocation(orgModel.getLocation());

        Set<Emp> emps = new HashSet<>();

        orgModel.getEmps().forEach(e -> {
            Emp emp = new Emp();
            emp.setEmpName(e.getEmpName());
            emp.setSalary(e.getSalary());
            emps.add(emp);
        });
        retrieveOrg.get().setEmps(emps);

        return null; // orgRepository.save(retrieveOrg);
        //return retrieveOrg;

    }

    @Override
    public void deleteOrgById(Long orgId) {
        orgRepository.deleteById(orgId);
    }
    @Override
    public Org insertOrg(OrgRequestModel orgModel){
        Org org = new Org();
        //org.setId(orgModel.getOrgId());
        org.setName(orgModel.getOrgName());
        org.setLocation(orgModel.getLocation());
        Set<Emp> emps = new HashSet<>();

        orgModel.getEmp().forEach(e -> {
            Emp emp = new Emp();
            emp.setEmpName(e.getEmpName());
            emp.setSalary(e.getSalary());
            emps.add(emp);
        });

        org.setEmps(emps);

        System.out.println(org.toString());

        return orgRepository.save(org);
    }
}
