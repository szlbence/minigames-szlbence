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

    @PostMapping("/cart/add")
    public void addCart(@RequestBody Cart newCart) {
        shopService.addNewCart(newCart);
    }

    @GetMapping("/carts")
    public List<Cart> getAllCarts() {
        return shopService.getAllCarts();
    }

    @PostMapping("/cart/addproductbox")
    public void addProductBoxToCart(@RequestBody Map<String, UUID> ids) {
        shopService.addProductBoxToCart(ids.get("productBoxId"), ids.get("cartId"));
    }

    @PostMapping("/cart/productboxes")
    public Map<ProductBox, Integer> getAllProductBoxesInCart(@RequestBody UUID cartId) {
        return shopService.getAllProductBoxesInCart(cartId);
    }

    @DeleteMapping("/cart/removeproductbox")
    public void removeProductBoxFromCart(@RequestBody Map<String, UUID> ids) {
        shopService.removeProductBoxFromCart(ids.get("productBoxId"), ids.get("cartId"));
    }

    @PostMapping("/cart/find")
    public Cart findCart(@RequestBody UUID cartId) {
        return shopService.findCart(cartId);
    }

    @DeleteMapping("/cart/remove")
    public void removeCart(@RequestBody UUID uuid) {
        shopService.removeCart(uuid);
    }

    @PostMapping("/cart/value")
    public BigDecimal getCartValue(@RequestBody UUID uuid) {
        return shopService.getCartValue(uuid);
    }

    @DeleteMapping("/productbox/removeproduct")
    public void removeProductFromProductBox(@RequestBody Map<String, UUID> ids) {
        shopService.removeProductFromProductBox(ids.get("productId"), ids.get("boxId"));
    }
}
