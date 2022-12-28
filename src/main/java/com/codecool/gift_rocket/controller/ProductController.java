package com.codecool.gift_rocket.controller;

import com.codecool.gift_rocket.model.Category;
import com.codecool.gift_rocket.model.Product;
import com.codecool.gift_rocket.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ProductController {

        @Autowired
        public ShopService shopService;

        @GetMapping("/products")
        public List<Product> getAllProducts() {
                return shopService.getAllProducts();
        }

        @PostMapping("/products")
        public void addProduct(@RequestBody Product newProduct) {
                shopService.addNewProduct(newProduct);
        }


        @GetMapping("/products/{id}")
        public Product findProduct(@PathVariable UUID id) {
            return shopService.findProduct(id);
        }

        @DeleteMapping("/products/{id}")
        public void removeProduct(@PathVariable UUID id) {
                shopService.removeProduct(id);
        }

        @GetMapping("/products/category/{category}")
        public List<Product> getProductsByCategory(@PathVariable Category category) {
                return shopService.getProductsByCategory(category);
        }

}
