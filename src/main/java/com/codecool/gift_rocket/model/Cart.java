package com.codecool.gift_rocket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.*;

public class Cart {

    private BigDecimal totalPrice;

    private static final String CURRENCY = "HUF";
    private final UUID id;
    private Map<ProductBox, Integer> productBoxes;
    public Cart() {
        this.id = UUID.randomUUID();
        this.productBoxes = new HashMap<>();
        this.totalPrice = new BigDecimal(0);
    }


    public BigDecimal getTotalPrice() {
        return totalPrice;
    }


    public UUID getId() {
        return id;
    }

    @JsonIgnore
    public Map<ProductBox, Integer> getProductBoxes() {
        return productBoxes;
    }

    public void addProductBox(ProductBox productBox) {
        if (productBoxes.containsKey(productBox)) {
            productBoxes.put(productBox, productBoxes.get(productBox) + 1);
        } else {
            productBoxes.put(productBox, 1);
        }
        totalPrice = totalPrice.add(productBox.getTotalPrice());
    }
}
