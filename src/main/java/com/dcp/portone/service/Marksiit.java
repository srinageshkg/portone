package com.dcp.portone.service;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class Marksiit {
    private Integer maths;
    private Integer physics;
    private Integer chemistry;
    private Integer total;
}
