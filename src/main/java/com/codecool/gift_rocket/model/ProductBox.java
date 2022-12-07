package com.codecool.gift_rocket.model;

import java.math.BigDecimal;
import java.util.*;

public class ProductBox {
    // price of packaging
    private BigDecimal packagingPrice;
    // price of packaging plus the product prices
    private BigDecimal totalPrice;
    private static final String CURRENCY = "HUF";
    private final UUID id;
    private String name;
    private String description;
    private Set<Category> categories;
    private Map<Product, Integer> products;

    public ProductBox(BigDecimal packagingPrice, UUID id, String name, String description) {
        this.packagingPrice = packagingPrice;
        this.totalPrice = packagingPrice;
        this.id = id;
        this.name = name;
        this.description = description;
        this.categories = new HashSet<>();
        this.products = new HashMap<>();
    }

    public BigDecimal getPackagingPrice() {
        return packagingPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void addProduct(Product product){
        products.put(product, 1);
        totalPrice = totalPrice.add(product.getPrice());
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }
}
