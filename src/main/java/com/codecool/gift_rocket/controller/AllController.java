package com.codecool.gift_rocket.controller;

import com.codecool.gift_rocket.model.Cart;
import com.codecool.gift_rocket.model.Category;
import com.codecool.gift_rocket.model.Product;
import com.codecool.gift_rocket.model.ProductBox;
import com.codecool.gift_rocket.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class AllController {

        @Autowired
        public ShopService shopService;

        @PostMapping("/products/add")
        public void addProduct(@RequestBody Product newProduct) {
                shopService.addNewProduct(newProduct);
        }

        @GetMapping("/products")
        public List<Product> getAllProducts() {
                return shopService.getAllProducts();
        }

        @PostMapping("/products/find")
        public Product findProduct(@RequestBody UUID uuid) {
            return shopService.findProduct(uuid);
        }

        @DeleteMapping("/products/remove")
        public void removeProduct(@RequestBody UUID uuid) {
                shopService.removeProduct(uuid);
        }

        @PostMapping("/products/category")
        public List<Product> getProductsByCategory(@RequestBody Category category) {
                return shopService.getProductsByCategory(category);
        }

        @PostMapping("/productbox/addproduct")
        public void addProductToProductBox(@RequestBody Product newProduct, @RequestBody UUID boxId) {
                shopService.addProductToProductBox(newProduct, boxId);
        }

        @PostMapping("/productbox/add/")
        public void addProductBox(@RequestBody ProductBox productBox) {
                shopService.addNewProductBox(productBox);
        }

        @PostMapping("/productbox/find")
        public ProductBox findProductBox(@RequestBody UUID uuid) {
                return shopService.findProductBox(uuid);
        }

        @DeleteMapping("/productbox/remove")
        public void removeProductBox(@RequestBody UUID uuid) {
                shopService.removeProductBox(uuid);
        }

        @PostMapping("/productbox/products")
        public Map<Product, Integer> getAllProductsInBox(@RequestBody UUID boxID) {
                return shopService.getAllProductsInBox(boxID);
        }

        @PostMapping("/productbox/value")
        public BigDecimal getProductBoxValue(@RequestBody UUID uuid) {
                return shopService.getProductBoxValue(uuid);
        }

        @PostMapping("/cart/add")
        public void addCart(@RequestBody Cart newCart) {
                shopService.addNewCart(newCart);
        }

        @GetMapping("/carts")
        public List<Cart> getAllCarts() {
                return shopService.getAllCarts();
        }

        @PostMapping("/cart/add-product-box")
        public void addProductBoxToCart(@RequestBody UUID boxId, @RequestBody UUID cartId) {
                shopService.addProductBoxToCart(boxId, cartId);
        }

        @PostMapping("/cart/find")
        public Cart findCart(@RequestBody UUID cartId) {
                return shopService.findCart(cartId);
        }

        @DeleteMapping("/cart/remove")
        public void removeCart(@RequestBody UUID uuid) {
                shopService.removeCart(uuid);
        }

}
