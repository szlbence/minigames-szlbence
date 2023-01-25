package com.codecool.gift_rocket.controller;

import com.codecool.gift_rocket.service.CartBoxService;
import com.codecool.gift_rocket.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cart")
public class NewestAndBestestCartController {

    @Autowired
    public CartService cartService;

    @Autowired
    public CartBoxService cartBoxService;

//    @GetMapping
//    public List<Cart> getAllCarts() {
//        return cartService.getAllCarts();
//    }
//
//    @PostMapping
//    public void addCart(@RequestBody Cart newCart) {
//        cartService.addNewCart(newCart);
//    }
//    @GetMapping("/{cartId}")
//    public Cart findCartById(@PathVariable Long cartId) {
//        return cartService.findCartById(cartId);
//    }
//
//    @DeleteMapping("/{cartId}")
//    public void removeCart(@PathVariable Long cartId) {
//        cartService.removeCartById(cartId);
//    }
//
//    @GetMapping("/value/{cartId}")
//    public BigDecimal getCartValue(@PathVariable Long cartId) {
//        return cartService.getCartValue(cartId);
//    }
//
//    @GetMapping("/{cartId}/product-boxes")
//    public List<ProductBox> getAllProductBoxesInCart(@PathVariable Long cartId) {
//        return cartService.getAllProductBoxesInCart(cartId);
//    }
//
    @PostMapping("/{cartId}/add/{productBoxId}")
    public void addProductBoxToCart(@PathVariable Long cartId, @PathVariable Long productBoxId) {
        cartBoxService.addProductBoxToCart(productBoxId, cartId);
    }
//
//    @DeleteMapping("/{cartId}/remove/{productBoxId}")
//    public void removeProductBoxFromCart(@PathVariable Long cartId, @PathVariable Long productBoxId) {
//        cartService.removeProductBoxFromCart(productBoxId,cartId);
//    }
//
//    @PutMapping("/{cartId}/remove/{productBoxId}")
//    public void removeLastProductBoxFromCart(@PathVariable Long cartId, @PathVariable Long productBoxId) {
//        cartService.removeLastProductBoxFromCart(productBoxId,cartId);
//    }
}
