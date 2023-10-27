package com.dcp.portone.leet;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
class Stock {
    private String name;
    private Double price;
}