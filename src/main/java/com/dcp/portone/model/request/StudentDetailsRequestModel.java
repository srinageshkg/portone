package com.dcp.portone.model.request;

import lombok.*;
import org.springframework.lang.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentDetailsRequestModel {
        private Integer cardNo;
        private String name;
        private String gender;
        private String dateOfBirth;
        private String cityTown;
        private Integer mathsMarks;
        private Integer physicsMarks;
        private Integer chemistryMarks;
        private Integer totalMarks;
}
