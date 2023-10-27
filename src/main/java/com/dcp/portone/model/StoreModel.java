package com.dcp.portone.model;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StoreModel {
    private String storeName;
    private String location;
}
