package com.dcp.portone.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentRest {
    private Integer cardNo;
    private String name;
    private String gender;
    private String dateOfBirth;
    private String cityTown;
    private Integer mathsMarks;
    private Integer physicsMarks;
    private Integer chemistryMarks;
}
