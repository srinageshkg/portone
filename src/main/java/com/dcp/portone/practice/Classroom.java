package com.dcp.portone.practice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Classroom {
    public enum CRTypes {LECTURE, DEMO, LAB, CONF}
    private String name;
    private CRTypes type;
}
