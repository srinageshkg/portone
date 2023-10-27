package com.dcp.portone.controller;

import com.dcp.portone.model.EmployeeModel;
import com.dcp.portone.model.OrgRequestModel;
import com.dcp.portone.model.OrgnizationModel;
import com.dcp.portone.model.response.ResponseHandler;
import com.dcp.portone.service.EmpService;
import com.dcp.portone.service.OrgServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/org")
public class OrgController {
    @Autowired
    private OrgServiceImpl orgServiceImpl;
    private EmpService empService;

    public OrgController(OrgServiceImpl orgServiceImpl, EmpService empService){
        this.orgServiceImpl = orgServiceImpl;
        this.empService = empService;
    }
    @GetMapping(path="")
    public List<OrgnizationModel> getOrgs(@RequestParam(value = "page", defaultValue = "1") int page,
                                         @RequestParam(value = "limit", defaultValue = "25") int limit){
        return orgServiceImpl.getOrgs();
    }

    @GetMapping(path = "/{Id}")
    public ResponseEntity<OrgnizationModel> getOrgById(@PathVariable Long Id){
        return new ResponseEntity<OrgnizationModel>(orgServiceImpl.getOrgById(Id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Object> updateOrganization(@RequestBody @Valid OrgRequestModel orgRecord) throws Exception {
        if (orgRecord == null || orgRecord.getOrgId() == null) {
            throw new Exception("Org Name must not be null.");
        }

        Optional<OrgnizationModel> optionalOrg = Optional.ofNullable(orgServiceImpl.getOrgById(orgRecord.getOrgId()));
        if (!optionalOrg.isPresent()) {
            throw new Exception("Org with Id : " + orgRecord.getOrgId() + " Not Found.");
        }

        OrgnizationModel orgPresent = optionalOrg.get();
        orgPresent.setOrgId(orgRecord.getOrgId());
        orgPresent.setEmps(orgRecord.getEmp());
        orgPresent.setLocation(orgRecord.getLocation());
        orgPresent.setOrgName(orgRecord.getOrgName());

        String message = "OrgDetails Updated!";
        return ResponseHandler.responseBuilder(message, HttpStatus.OK, orgServiceImpl.updateOrg(orgPresent));
    }

    @PostMapping(path = "/addorg", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> addOrg(@Valid @RequestBody OrgRequestModel orgDetails){
        System.out.println(orgDetails.toString());
        OrgRequestModel returnValue = new OrgRequestModel();
        returnValue.setOrgName(orgDetails.getOrgName());
        returnValue.setLocation(orgDetails.getLocation());

        Set<EmployeeModel> employeeModel = new HashSet<>();

        orgDetails.getEmp().forEach(e ->{
            employeeModel.add(e);
        });

        returnValue.setEmp(employeeModel);
        String message = "OrgDetails Insert";

        //orgService.insertOrg(orgDetails);
        return ResponseHandler.responseBuilder(message, HttpStatus.OK, orgServiceImpl.insertOrg(orgDetails));
        // return new ResponseEntity<OrgnizationModel>(returnValue, HttpStatus.OK);
    }
}
