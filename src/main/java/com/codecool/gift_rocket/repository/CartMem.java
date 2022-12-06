package com.codecool.gift_rocket.repository;

import com.codecool.gift_rocket.model.Cart;
import com.codecool.gift_rocket.model.ProductBox;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CartMem implements CartDao{

    private List<Cart> carts = new ArrayList<>();

    @Override
    public void addProductBox(ProductBox productBox, UUID cartId) {

    }

    @Override
    public void addNewCart(Cart cart) {

    }

    @Override
    public Cart find(UUID cartId) {
        return null;
    }

    @Override
    public void removeProductBox(ProductBox productBox, UUID cartId) {

    }

    @Override
    public Map<ProductBox, Integer> getAllProductBoxesInCart(UUID cartId) {
        return null;
    }

    @Override
    public void removeCart(UUID cartId) {

    }

    @Override
    public BigDecimal getCartValue(UUID cartId) {
        return null;
    }
}
