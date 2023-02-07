package com.codecool.gift_rocket.service;

import com.codecool.gift_rocket.model.Category;
import com.codecool.gift_rocket.model.Product;
import com.codecool.gift_rocket.repository.JPA.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void addNewProduct(Product product) {
        productRepository.save(product);
    }

    public Product findProductById(Long productId) {
        return productRepository
                .findById(productId)
                .orElseThrow(() -> new NoSuchElementException("No product found by given id"));
    }

    public void removeProductById(Long productId) {
        Product foundProduct = findProductById(productId);
        productRepository.delete(foundProduct);
    }

    public BigDecimal getProductValue(Long productId) {
        Product foundProduct = findProductById(productId);
        return foundProduct.getPrice();
    }

    public List<Product> getProductsByCategory(Category category) {
        return productRepository.findAllByCategory(category);
    }
}

