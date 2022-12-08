package com.codecool.gift_rocket.model;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


public class ProductBoxTest {
    static final BigDecimal TEST_PRODUCT_BOX_PACKAGING_PRICE = new BigDecimal(50);
    static final String TEST_PRODUCT_BOX_NAME = "Test box";
    static final String TEST_PRODUCT_BOX_DESCRIPTION = "Basic product box for testing purposes";
    static final BigDecimal TEST_PRODUCT_PRICE = new BigDecimal(50);
    static final String TEST_PRODUCT_NAME = "Chocolate";
    static final String TEST_PRODUCT_DESCRIPTION = "Basic chocolate for testing purposes";
    static final Category TEST_PRODUCT_CATEGORY = Category.SWEET;

    static  final ProductBox productBox = new ProductBox(TEST_PRODUCT_BOX_PACKAGING_PRICE, TEST_PRODUCT_BOX_NAME, TEST_PRODUCT_BOX_DESCRIPTION);
    static  final Product product = new Product(TEST_PRODUCT_PRICE, TEST_PRODUCT_NAME, TEST_PRODUCT_DESCRIPTION, TEST_PRODUCT_CATEGORY);

    @Test
    void testGetPackagingPrice(){
        assertEquals(TEST_PRODUCT_BOX_PACKAGING_PRICE, productBox.getPackagingPrice());
    }

    @Test
    void testGetName(){
        assertEquals(TEST_PRODUCT_BOX_NAME, productBox.getName());
    }

    @Test
    void testGetDescription(){
        assertEquals(TEST_PRODUCT_BOX_DESCRIPTION, productBox.getDescription());
    }

    @Test
    void testAddAndRemoveProduct(){
        productBox.addProduct(product);
        assertTrue(productBox.getProducts().containsKey(product));
        productBox.removeProduct(product);
        assertFalse(productBox.getProducts().containsKey(product));
    }

    @Test
    void testGetTotalPrice(){
        BigDecimal expectedTotalPrice = product.getPrice().add(productBox.getPackagingPrice());
        assertEquals(expectedTotalPrice, productBox.getTotalPrice());
        productBox.removeProduct(product);
        BigDecimal expectedNewTotalPrice = productBox.getPackagingPrice();
        assertEquals(expectedNewTotalPrice, productBox.getTotalPrice());
    }

    @Test
    void testGetCategories(){
        productBox.addProduct(product);
        assertTrue(productBox.getCategories().contains(product.getCategory()));
    }

}
