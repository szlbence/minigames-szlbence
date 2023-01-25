package com.codecool.gift_rocket.controller;

import com.codecool.gift_rocket.model.Cart;
import com.codecool.gift_rocket.model.Product;
import com.codecool.gift_rocket.service.CartBoxService;
import com.codecool.gift_rocket.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("/cart")
public class CartController {

    private final int REMOVEPRODUCTFROMCART = -1;
    private final int ADDPRODUCTTOCART = 1;
    @Autowired
    public CartService cartService;

    @Autowired
    public CartBoxService cartBoxService;

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

    @GetMapping("/{cartId}/products")
    public List<Product> getAllProductsInCart(@PathVariable Long cartId) {
        return cartService.getAllProductsInCart(cartId);
    }

    @PostMapping("/{cartId}/add/{productId}")
    public void addProductToCart(@PathVariable Long cartId, @PathVariable Long productId) {
        cartService.changeProductInCart(productId, cartId, ADDPRODUCTTOCART);
    }

    @PutMapping("/{cartId}/remove/{productId}")
    public void removeProductFromCart(@PathVariable Long cartId, @PathVariable Long productId) {
        cartService.changeProductInCart(productId,cartId, REMOVEPRODUCTFROMCART);
    }

    @DeleteMapping("/{cartId}/remove/{productId}")
    public void deleteProductFromCart(@PathVariable Long cartId, @PathVariable Long productId) {
        cartService.deleteProductFromCart(productId,cartId);
    }
}
