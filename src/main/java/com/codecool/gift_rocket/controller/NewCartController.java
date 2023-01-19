package com.codecool.gift_rocket.controller;

import com.codecool.gift_rocket.model.Cart;
import com.codecool.gift_rocket.model.ProductBox;
import com.codecool.gift_rocket.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("/cart")
public class NewCartController {

    @Autowired
    public CartService cartService;

    @GetMapping
    public List<Cart> getAllCarts() {
        return cartService.getAllCarts();
    }

    @PostMapping
    public void addCart(@RequestBody Cart newCart) {
        cartService.addNewCart(newCart);
    }
    @GetMapping("/{cartId}")
    public Cart findCartById(@PathVariable Long cartId) {
        return cartService.findCartById(cartId);
    }

    @DeleteMapping("/{cartId}")
    public void removeCart(@PathVariable Long cartId) {
        cartService.removeCartById(cartId);
    }

    @GetMapping("/value/{cartId}")
    public BigDecimal getCartValue(@PathVariable Long cartId) {
        return cartService.getCartValue(cartId);
    }

    @GetMapping("/{cartId}/product-boxes")
    public List<ProductBox> getAllProductBoxesInCart(@PathVariable Long cartId) {
        return cartService.getAllProductBoxesInCart(cartId);
    }

    @PostMapping("/{cartId}/add/{productBoxId}")
    public void addProductBoxToCart(@PathVariable Long cartId, @PathVariable Long productBoxId) {
        cartService.addProductBoxToCart(productBoxId, cartId);
    }

    @DeleteMapping("/{cartId}/remove/{productBoxId}")
    public void removeProductBoxFromCart(@PathVariable Long cartId, @PathVariable Long productBoxId) {
        cartService.removeProductBoxFromCart(productBoxId,cartId);
    }

    @PutMapping("/{cartId}/remove/{productBoxId}")
    public void removeLastProductBoxFromCart(@PathVariable Long cartId, @PathVariable Long productBoxId) {
        cartService.removeLastProductBoxFromCart(productBoxId,cartId);
    }
}
