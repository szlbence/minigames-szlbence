package com.codecool.gift_rocket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.*;

public class Cart {

    private BigDecimal totalPrice;

    private final String name;

    private static final String CURRENCY = "HUF";

    private final UUID id;
    private Map<ProductBox, Integer> productBoxes = new HashMap<>();
    public Cart(String name) {
        this.id = UUID.randomUUID();
        this.productBoxes = new HashMap<>();
        this.totalPrice = new BigDecimal(0);
        this.name = name;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public String getName() {
        return name;
    }


    public UUID getId() {
        return id;
    }

//    @JsonIgnore
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

    public void removeProductBox(ProductBox productBox) {
        if (productBoxes.containsKey(productBox)) {
            productBoxes.put(productBox, productBoxes.get(productBox) - 1);
            totalPrice = totalPrice.subtract(productBox.getTotalPrice());
            if (productBoxes.get(productBox) == 0){
                productBoxes.remove(productBox);
            }
        }
    }

    @Override
    public String toString() {
        return name;
    }


}