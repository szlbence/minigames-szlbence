package com.codecool.gift_rocket.controller;

import com.codecool.gift_rocket.model.Category;
import com.codecool.gift_rocket.model.Product;
import com.codecool.gift_rocket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("/research")
public class ProductController {
    @Autowired
    public ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return  productService.getAllProducts();
    }
    @PostMapping
    public void addProduct(@RequestBody Product product) {
        productService.addNewProduct(product);
    }

    @GetMapping("/{productId}")
    public Product findProductById(@PathVariable Long productId) {
        return productService.findProductById(productId);
    }

    @DeleteMapping("/{productId}")
    public void removeProductById(@PathVariable Long productId) {
        productService.removeProductById(productId);
    }

    @GetMapping("/{productId}/value")
    public BigDecimal getProductValue(@PathVariable Long productId) {
        return productService.getProductValue(productId);
    }

    @GetMapping("/category/{category}")
    public List<Product> getAllProductsInBox(@PathVariable Category category) {
        return productService.getProductsByCategory(category);
    }
}
