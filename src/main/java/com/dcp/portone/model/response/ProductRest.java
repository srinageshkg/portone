package com.dcp.portone.model.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProductRest {
    private Long id;
    private String  productName;
    private String  category;
    private Float quantity;
    private Float price;
    private Float cost;
}
