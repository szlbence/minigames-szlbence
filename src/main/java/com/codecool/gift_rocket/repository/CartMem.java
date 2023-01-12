package com.codecool.gift_rocket.repository;

import com.codecool.gift_rocket.model.Cart;
import com.codecool.gift_rocket.model.ProductBox;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
@Repository
public class CartMem implements CartDao{

    private List<Cart> carts = new ArrayList<>();

    @Override
    public void addProductBox(ProductBox productBox, UUID cartId) {
        Cart foundCart = carts.stream().filter(b -> b.getId().equals(cartId)).findAny().orElse(null);
        if(foundCart != null){
            foundCart.addProductBox(productBox);
        }
    }

    @Override
    public void addNewCart(Cart cart) {
        carts.add(cart);
    }

    @Override
    public Cart find(UUID cartId) {
        return carts.stream().filter(b -> b.getId().equals(cartId)).findAny().orElse(null);
    }

    @Override
    public void removeProductBox(ProductBox productBox, UUID cartId) {
        Cart foundCart = carts.stream().filter(b -> b.getId().equals(cartId)).findAny().orElse(null);
        if(foundCart != null){
            foundCart.removeProductBox(productBox);
        }
    }

    @Override
    public Map<ProductBox, Integer> getAllProductBoxesInCart(UUID cartId) {
        Cart foundCart = carts.stream().filter(b -> b.getId().equals(cartId)).findAny().orElse(null);
        if(foundCart != null){
            return foundCart.getProductBoxes();
        }
        return null;
    }

    @Override
    public void removeCart(UUID cartId) {
        Cart foundCart = carts.stream().filter(b -> b.getId().equals(cartId)).findAny().orElse(null);
        if(foundCart != null){
            carts.remove(foundCart);
        }
    }

    @Override
    public BigDecimal getCartValue(UUID cartId) {
        Cart foundCart = carts.stream().filter(b -> b.getId().equals(cartId)).findAny().orElse(null);
        if(foundCart != null){
            return foundCart.getTotalPrice();
        }
        return BigDecimal.ZERO;
    }

    @Override
    public List<Cart> getCarts() {
        return carts;
    }
}
