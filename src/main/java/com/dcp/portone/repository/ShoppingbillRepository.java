package com.dcp.portone.repository;

import com.dcp.portone.entity.Shoppingbill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingbillRepository extends JpaRepository<Shoppingbill, Long> {
    Shoppingbill getShoppingbillById(Long Id);
    List<Shoppingbill> findByCustomerName(String customerName);
    List<Shoppingbill> findByStoreName(String storeName);
    List<Shoppingbill> findByStoreNameAndCustomerName(String store, String cust);
    List<Shoppingbill> findByCustomerNameIgnoreCaseContaining(String customerName);
    List<Shoppingbill> findByStoreNameIgnoreCaseContaining(String storeName);
}
