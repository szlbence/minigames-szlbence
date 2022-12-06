package com.codecool.gift_rocket.controller;

import com.codecool.gift_rocket.model.Product;
import com.codecool.gift_rocket.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
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
}
