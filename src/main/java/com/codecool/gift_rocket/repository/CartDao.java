package com.codecool.gift_rocket.repository;

import com.codecool.gift_rocket.model.Product;
import com.codecool.gift_rocket.model.ProductBox;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

public interface CartDao {

    void addProductBox(ProductBox productBox, UUID cartId);

    void addNewCart(Cart cart);

    Cart find(UUID cartId);

    void removeProductBox(ProductBox productBox, UUID cartId);

    Map<ProductBox, Integer> getAllProductBoxesInCart(UUID cartId);

    void removeCart(UUID cartId);

    BigDecimal getCartValue(UUID cartId);

}
