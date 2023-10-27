package com.dcp.portone.model.response;

import lombok.*;

@Builder
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Long empId;
    private String empName;
}
