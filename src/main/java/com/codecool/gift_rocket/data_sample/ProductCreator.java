package com.codecool.gift_rocket.data_sample;


import com.codecool.gift_rocket.model.Category;
import com.codecool.gift_rocket.model.Product;
import com.codecool.gift_rocket.repository.ProductMem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class ProductCreator {

    private ProductMem productMem;

    public void initialize() {
        Product product1 = new Product(BigDecimal.valueOf(40), "Wine", "A bottle of top shelf wine. Tasty!", Category.ALCOHOL);
        Product product2 = new Product(BigDecimal.valueOf(10), "Chocolate", "A bar of chocolate from our favorite purple brand!", Category.SWEET);
        Product product3 = new Product(BigDecimal.valueOf(25), "Water Balloons", "Global Warming? Get these water balloons for your kids!", Category.KID);
        productMem.add(product1);
        productMem.add(product2);
        productMem.add(product3);
    }

    @Autowired
    public ProductCreator(ProductMem productMem) {
        this.productMem = productMem;
        initialize();
    }
}
