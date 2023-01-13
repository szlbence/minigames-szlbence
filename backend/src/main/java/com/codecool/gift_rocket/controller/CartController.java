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
@RequestMapping("/cart")
@CrossOrigin(origins = "*")
public class CartController {

    @Autowired
    public ShopService shopService;

    @GetMapping
    public List<Cart> getAllCarts() {
        return shopService.getAllCarts();
    }

    @PostMapping
    public void addCart(@RequestBody Cart newCart) {
        shopService.addNewCart(newCart);
    }

    @GetMapping("/{id}")
    public Cart findCart(@PathVariable UUID id) {
        return shopService.findCart(id);
    }

    @DeleteMapping("/{id}")
    public void removeCart(@PathVariable UUID id) {
        shopService.removeCart(id);
    }

    @GetMapping("/{id}/value")
    public BigDecimal getCartValue(@PathVariable UUID id) {
        return shopService.getCartValue(id);
    }

    @GetMapping("/{id}/productboxes")
    public Map<ProductBox, Integer> getAllProductBoxesInCart(@PathVariable UUID id) {
        return shopService.getAllProductBoxesInCart(id);
    }

    @PostMapping("/{id}/add/{productBoxId}")
    public void addProductBoxToCart(@PathVariable UUID id, @PathVariable UUID productBoxId) {
        shopService.addProductBoxToCart(productBoxId, id);
    }

    @DeleteMapping("/{id}/remove/{productBoxId}")
    public void removeProductBoxFromCart(@PathVariable UUID id, @PathVariable UUID productBoxId) {
        shopService.removeProductBoxFromCart(productBoxId,id);
    }

    @GetMapping("/api/dadjokes")
    public String dadJokes() {
        return "Justice is a dish best served cold, if it were served warm it would be just water.";
    }
}
