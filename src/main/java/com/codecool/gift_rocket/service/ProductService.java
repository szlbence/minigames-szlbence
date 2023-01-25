//package com.codecool.gift_rocket.service;
//
//import com.codecool.gift_rocket.model.Category;
//import com.codecool.gift_rocket.model.Product;
//import com.codecool.gift_rocket.model.ProductBox;
//import com.codecool.gift_rocket.repository.JPA.ProductRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.NoSuchElementException;
//import java.util.Optional;
//
//@Service
//public class ProductService {
//    @Autowired
//    ProductRepository productRepository;
//    public List<Product> getAllProducts() {
//        return (List<Product>) productRepository.findAll();
//    }
//
//    public void addNewProduct(Product newProduct) {
//        productRepository.save(newProduct);
//    }
//
//    public Product findProductById(Long productId) {
//        Optional<Product> foundProduct = productRepository.findById(productId);
//        if(foundProduct.isPresent()){
//            return foundProduct.get();
//        }
//        else {
//            throw new NoSuchElementException("No product found by given id");
//        }
//    }
//
//    public void removeProductById(Long productId) {
//        Optional<Product> foundProduct = productRepository.findById(productId);
//        if(foundProduct.isPresent()){
//            productRepository.deleteById(productId);
//        }
//        else {
//            throw new NoSuchElementException("No product found by given id");
//        }
//    }
//
//    public List<Product> getProductsByCategory(Category category) {
//        return productRepository.findAllByCategory(category);
//    }
//}
