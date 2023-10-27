package com.dcp.portone.model.response;

import com.dcp.portone.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopRest {
    private Long Id;
    private String shopName;
    private String custName;
    private Date dateCreated;
    private Float totalAmount;
    private Set<ProductRest> products;
    private Store store;
}
