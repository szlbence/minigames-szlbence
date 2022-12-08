package com.codecool.gift_rocket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    public ProductBox(BigDecimal packagingPrice, String name, String description) {
        this.packagingPrice = packagingPrice;
        this.totalPrice = packagingPrice;
        this.id = UUID.randomUUID();
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
@JsonIgnore
    public Set<Category> getCategories() {
        return categories;
    }
@JsonIgnore
    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void addProduct(Product product){
        if (products.containsKey(product)) {
            products.put(product, products.get(product) + 1);
        } else {
            products.put(product, 1);
        }
        totalPrice = totalPrice.add(product.getPrice());
        categories.add(product.getCategory());
    }

    public void removeProduct(Product product) {
        products.put(product, products.get(product) - 1);
        if (products.get(product) == 0){
            products.remove(product);
        }
        totalPrice = totalPrice.subtract(product.getPrice());
        categories.remove(product.getCategory());
    }


}
