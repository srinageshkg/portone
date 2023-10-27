package com.dcp.portone.controller;

import com.dcp.portone.exceptions.ObjNotFoundException;
import com.dcp.portone.model.EmployeeModel;
import com.dcp.portone.service.EmpService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/emps")
public class EmployeeController {
    private EmpService empService;
    @GetMapping(path="/org/{Id}")
    public Set<EmployeeModel> getEmps(@RequestBody Long Id,
                                      @RequestParam(value = "page", defaultValue = "1") int page,
                                      @RequestParam(value = "limit", defaultValue = "25") int limit){

        if (Id > 10000 || Id < 0) {
            throw new ObjNotFoundException("Id No not found - " + Id);
        }
        return (Set<EmployeeModel>) empService.getEmpsByOrgId(Id);
    }
}
