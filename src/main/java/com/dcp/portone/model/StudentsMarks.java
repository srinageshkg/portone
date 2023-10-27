package com.dcp.portone.model;

import lombok.Builder;

@Builder
public class StudentsMarks {
    private Long studentId;
    private Integer cardNo;
    private String name;
    private Character gender;
    private String dateOfBirth;
    private String cityTown;
    private Integer maths;
    private Integer physics;
    private Integer chemistry;
}
