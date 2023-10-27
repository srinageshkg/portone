package com.dcp.portone.service;

import com.dcp.portone.entity.Emp;
import com.dcp.portone.model.EmployeeModel;
import com.dcp.portone.repository.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EmpService {
    @Autowired
    private EmpRepository empRepository;

    public List<EmployeeModel> getEmpsByOrgId(Long orgId) {
        Optional<Emp> emps = Optional.ofNullable(empRepository.findById(orgId).orElseThrow(NoSuchElementException::new));

        List<EmployeeModel> employeeModelList = new ArrayList<>();
        emps.stream().forEach(e -> {
            EmployeeModel employeeModel = EmployeeModel.builder()
                    .empId(e.getId())
                    .empName(e.getEmpName())
                    .salary(e.getSalary())
                    .build();
            employeeModelList.add(employeeModel);
        });

        return employeeModelList;
    }
}
