package com.codecool.gift_rocket.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Cart {

    private BigDecimal totalPrice;
    private static final String CURRENCY = "HUF";
    private final UUID id;
    private List<ProductBox> productBoxes;

    public Cart(UUID id) {
        this.id = id;
        this.productBoxes = new ArrayList<>();
        this.totalPrice = new BigDecimal(0);
    }
}
