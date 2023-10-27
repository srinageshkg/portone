package com.dcp.portone.service;

import com.dcp.portone.entity.Product;
import com.dcp.portone.entity.Shoppingbill;

import java.util.Set;

public interface ProductService {
    public abstract Set<Product> getAllProducts(Shoppingbill shoppingbill);
    public abstract void deleteProduct(Product product);
}
