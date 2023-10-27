package com.dcp.portone.model;

import lombok.*;

import java.security.PrivateKey;
import java.util.Set;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrgnizationModel {
    private Long orgId;
    private String orgName;
    private String location;

    private Set<EmployeeModel> emps;
}
