package com.dcp.portone.model;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeModel {
    private Long empId;
    private String empName;
    private Long salary;
}
