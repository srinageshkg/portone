package com.dcp.portone.service;

import com.dcp.portone.entity.Product;
import com.dcp.portone.entity.Shoppingbill;
import com.dcp.portone.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    public Set<Product> getAllProducts(Shoppingbill shoppingbill){
        Set<Product> products = shoppingbill.getProducts();
        return products;
    }

    public void deleteProduct(Product product){
    }
}
