package com.dcp.portone.model;

import com.dcp.portone.entity.Emp;
import lombok.*;

import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrgRequestModel {
    private Long orgId;
    private String orgName;
    private String location;
    private Set<EmployeeModel> emp;
}
