package com.dcp.portone.service;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class Studentiit {
    private Integer cardNo;
    private String name;
    private Character gender;
    private String dateOfBirth;
    private String city;
    private Marksiit marksiit;
}
