package com.dcp.portone.repository;

import com.dcp.portone.entity.Product;
import com.dcp.portone.entity.Shoppingbill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
