package com.codecool.gift_rocket.controller;

import com.codecool.gift_rocket.model.Category;
import com.codecool.gift_rocket.model.Product;
import com.codecool.gift_rocket.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*")
public class ProductController {

        @Autowired
        public ShopService shopService;

        @GetMapping
        public List<Product> getAllProducts() {
                return shopService.getAllProducts();
        }

        @PostMapping
        public void addProduct(@RequestBody Product newProduct) {
                shopService.addNewProduct(newProduct);
        }


        @GetMapping("/{id}")
        public Product findProduct(@PathVariable UUID id) {
            return shopService.findProduct(id);
        }

        @DeleteMapping("/{id}")
        public void removeProduct(@PathVariable UUID id) {
                shopService.removeProduct(id);
        }

        @GetMapping("/category/{category}")
        public List<Product> getProductsByCategory(@PathVariable Category category) {
                return shopService.getProductsByCategory(category);
        }

}
