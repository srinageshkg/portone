package com.dcp.portone.service;

import com.dcp.portone.entity.Shoppingbill;
import com.dcp.portone.model.response.ShopRest;

import java.util.List;
import java.util.Set;

public interface ShoppingbillService {
    abstract Set<ShopRest> getAllBills();
    abstract ShopRest getBillByShopRest(Shoppingbill sBill);
    abstract Set<ShopRest> getShoppingbillByStore(String store);
    abstract ShopRest getShoppingbillById(Long Id);
    abstract List<Shoppingbill> getShoppingbillByCustomerName(String customerName);
    abstract List<Shoppingbill> getShoppingbillByCustomerNameContaining(String customerName);
    abstract List<Shoppingbill> findByStoreAndCustomerName(String storeName, String cust);
    abstract Shoppingbill saveShoppingBill(ShopRest sr);

}
