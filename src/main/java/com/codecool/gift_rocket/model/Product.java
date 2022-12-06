package com.codecool.gift_rocket.model;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {
    private BigDecimal price;
    private static final String CURRENCY = "HUF";
    private final UUID id;
    private String name;
    private String description;
    private Category category;

    public Product(BigDecimal price, String name, String description, Category category) {
        this.price = price;
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.category = category;
    }
}
