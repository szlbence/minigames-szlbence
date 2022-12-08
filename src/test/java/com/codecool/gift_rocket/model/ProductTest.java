package com.codecool.gift_rocket.model;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;


import static org.junit.jupiter.api.Assertions.assertEquals;


public class ProductTest {
    static final BigDecimal TEST_PRODUCT_PRICE = new BigDecimal(50);
    static final String TEST_PRODUCT_NAME = "Chocolate";
    static final String TEST_PRODUCT_DESCRIPTION = "Basic chocolate for testing purposes";
    static final Category TEST_PRODUCT_CATEGORY = Category.SWEET;
    static final BigDecimal TEST_NEW_PRICE = new BigDecimal(100);
    static final String TEST_NEW_NAME = "New Chocolate";
    static final String TEST_NEW_DESCRIPTION = "Updated chocolate for testing purposes";
    static final Category TEST_NEW_CATEGORY = Category.KID;
    Product product = new Product(TEST_PRODUCT_PRICE, TEST_PRODUCT_NAME, TEST_PRODUCT_DESCRIPTION, TEST_PRODUCT_CATEGORY);

    @Test
    void testGetPrice(){
        assertEquals(TEST_PRODUCT_PRICE, product.getPrice());
    }

    @Test
    void testGetName(){
        assertEquals(TEST_PRODUCT_NAME, product.getName());
    }

    @Test
    void testGetDescription(){
        assertEquals(TEST_PRODUCT_DESCRIPTION, product.getDescription());
    }

    @Test
    void testGetCategory(){
        assertEquals(TEST_PRODUCT_CATEGORY, product.getCategory());
    }

    @Test
    void testGetId() {
        assertEquals(TEST_PRODUCT_CATEGORY, product.getCategory());
    }

    @Test
    void testSetPrice(){
        product.setPrice(TEST_NEW_PRICE);
        assertEquals(TEST_NEW_PRICE, product.getPrice());
    }

    @Test
    void testSetName(){
        product.setName(TEST_NEW_NAME);
        assertEquals(TEST_NEW_NAME, product.getName());
    }

    @Test
    void testSetDescription(){
        product.setDescription(TEST_NEW_DESCRIPTION);
        assertEquals(TEST_NEW_DESCRIPTION, product.getDescription());
    }

    @Test
    void testSetCategory(){
        product.setCategory(TEST_NEW_CATEGORY);
        assertEquals(TEST_NEW_CATEGORY, product.getCategory());
    }
}
