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
    private List<Product> products;

    public ProductBox(BigDecimal packagingPrice, UUID id, String name, String description) {
        this.packagingPrice = packagingPrice;
        this.id = id;
        this.name = name;
        this.description = description;
        this.categories = new HashSet<>();
        this.products = new ArrayList<>();
    }
}
