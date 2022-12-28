package com.codecool.gift_rocket.controller;

import com.codecool.gift_rocket.model.Cart;
import com.codecool.gift_rocket.model.ProductBox;
import com.codecool.gift_rocket.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class CartController {

    @Autowired
    public ShopService shopService;

    @GetMapping("/cart")
    public List<Cart> getAllCarts() {
        return shopService.getAllCarts();
    }

    @PostMapping("/cart")
    public void addCart(@RequestBody Cart newCart) {
        shopService.addNewCart(newCart);
    }

    @GetMapping("/cart/{id}")
    public Cart findCart(@PathVariable UUID id) {
        return shopService.findCart(id);
    }

    @DeleteMapping("/cart/{id}")
    public void removeCart(@PathVariable UUID id) {
        shopService.removeCart(id);
    }

    @GetMapping("/cart/{id}/value")
    public BigDecimal getCartValue(@PathVariable UUID id) {
        return shopService.getCartValue(id);
    }

    @GetMapping("/cart/{id}/productboxes")
    public Map<ProductBox, Integer> getAllProductBoxesInCart(@PathVariable UUID id) {
        return shopService.getAllProductBoxesInCart(id);
    }

    @PostMapping("/cart/{id}/add/{productBoxId}")
    public void addProductBoxToCart(@PathVariable UUID id, @PathVariable UUID productBoxId) {
        shopService.addProductBoxToCart(productBoxId, id);
    }

    @DeleteMapping("/cart/{id}/remove/{productBoxId}")
    public void removeProductBoxFromCart(@PathVariable UUID id, @PathVariable UUID productBoxId) {
        shopService.removeProductBoxFromCart(productBoxId,id);
    }
}
