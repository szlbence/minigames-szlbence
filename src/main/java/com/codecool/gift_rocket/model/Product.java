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

    public BigDecimal getPrice() {
        return price;
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

    public Category getCategory() {
        return category;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

//    @Override
//    public String toString() {
//        return "Product{" +
//                "price=" + price +
//                ", id=" + id +
//                ", name='" + name + '\'' +
//                ", description='" + description + '\'' +
//                ", category=" + category +
//                '}';
//    }
}
