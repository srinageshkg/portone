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
public class Prof {
    public enum PRank{SENIOR, JUNIOR, DEAN}
    private String name;
    private String subject;
    private PRank rank;

}
