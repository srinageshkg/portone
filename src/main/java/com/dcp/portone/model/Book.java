package com.dcp.portone.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class Book {
    private Long rowNo;
    private String name;
    private String author;
    private String genre;
    private String language;
    private Integer pages;
    private String publisher;
    private Integer year;
    private Integer yearlySales;
    private Double price;
}


