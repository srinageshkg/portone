package com.dcp.portone.repository;

import com.dcp.portone.entity.Product;
import com.dcp.portone.entity.Shoppingbill;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@SpringBootTest
public class ShoppingbillRepositoryTest {
    @Autowired
    ShoppingbillRepository shoppingbillRepository;

    @Test
    public void saveShoppingBill(){

        // Create Shoppingbill Object
        Shoppingbill bill = new Shoppingbill();
        Product item1 = new Product();

        item1.setProductName("Sofa");
        item1.setCategory("Furniture");
        item1.setQuantity(1F);
        item1.setPrice(3599.99F);
        item1.setCost(3899.99F);
        item1.setShoppingbill(bill);

        Product item2 = new Product();

        item2.setProductName("Car");
        item2.setCategory("Utility");
        item2.setQuantity(2F);
        item2.setPrice(500.99F);
        item2.setCost(1299.99F);
        item2.setShoppingbill(bill);

        bill.setProducts(Set.of(item1, item2));

        bill.setCustomerName("Ranga");
        bill.setStoreName("KMart");
//        bill.setTotalCost(bill.getTotalAmount());

        shoppingbillRepository.save(bill);

    }

    /*

    @Test
    void testGetAllShoppingbiils(){
        List<Shoppingbill> bills = shoppingbillRepository.findAll();

        bills.forEach((bill) -> {
            System.out.println("Bill Id: " + bill.getBillID());
            System.out.println("Product Size: " + bill.getProducts().size());

            bill.getProducts().forEach((product -> {
                System.out.println("Product Name: " + product.getProductName());
            }));
        });
    }*/
}
