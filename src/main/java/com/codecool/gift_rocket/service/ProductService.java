//package com.codecool.gift_rocket.service;
//
//import com.codecool.gift_rocket.model.Cart;
//import com.codecool.gift_rocket.model.Product;
//import com.codecool.gift_rocket.model.ProductBox;
//import com.codecool.gift_rocket.repository.JPA.ProductBoxRepository;
//import com.codecool.gift_rocket.repository.JPA.ProductRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.NoSuchElementException;
//import java.util.Optional;
//
//@Service
//public class ProductBoxService {
//    @Autowired
//    ProductBoxRepository productBoxRepository;
//    @Autowired
//    ProductRepository productRepository;
//    public List<ProductBox> getAllProductBoxes() {
//        return (List<ProductBox>) productBoxRepository.findAll();
//    }
//
//    public void addNewProductBox(ProductBox productBox) {
//        productBox.setTotalPrice(productBox.getPackagingPrice());
//        productBoxRepository.save(productBox);
//    }
//
//    public ProductBox findProductBoxById(Long productBoxId) {
//        Optional<ProductBox> foundProductBox = productBoxRepository.findById(productBoxId);
//        if(foundProductBox.isPresent()){
//            return foundProductBox.get();
//        }
//        else {
//            throw new NoSuchElementException("No product box found by given id");
//        }
//    }
//
//    public void removeProductBoxById(Long productBoxId) {
//        Optional<ProductBox> foundProductBox = productBoxRepository.findById(productBoxId);
//        if(foundProductBox.isPresent()){
//            productBoxRepository.delete(foundProductBox.get());
//        }
//        else {
//            throw new NoSuchElementException("No product box found by given id");
//        }
//    }
//
//    public BigDecimal getProductBoxValue(Long productBoxId) {
//        Optional<ProductBox> foundProductBox = productBoxRepository.findById(productBoxId);
//        if(foundProductBox.isPresent()){
//            return foundProductBox.get().getTotalPrice();
//        }
//        else {
//            throw new NoSuchElementException("No product box found by given id");
//        }
//    }
//
//    public List<Product> getAllProductsInBox(Long productBoxId) {
//        Optional<ProductBox> foundProductBox = productBoxRepository.findById(productBoxId);
//        if(foundProductBox.isPresent()){
//            return foundProductBox.get().getProducts();
//        }
//        else {
//            throw new NoSuchElementException("No product box found by given id");
//        }
//    }
//
//    public void addProductToProductBox(Long productId, Long productBoxId) {
//        Optional<ProductBox> foundProductBox = productBoxRepository.findById(productBoxId);
//        Optional<Product> foundProduct = productRepository.findById(productId);
//        if(foundProductBox.isPresent()){
//            if(foundProduct.isPresent()){
//                foundProductBox.get().addProduct(foundProduct.get());
//                productBoxRepository.save(foundProductBox.get());
//            }
//            else {
//                throw new NoSuchElementException("No product box found by given id");
//            }
//        }
//        else {
//            throw new NoSuchElementException("No cart found by given id");
//        }
//    }
//
//    public void removeProductFromProductBox(Long productId, Long productBoxId) {
//        Optional<ProductBox> foundProductBox = productBoxRepository.findById(productBoxId);
//        Optional<Product> foundProduct = productRepository.findById(productId);
//        if(foundProductBox.isPresent()){
//            if(foundProduct.isPresent()){
//                foundProductBox.get().removeProduct(foundProduct.get());
//                productBoxRepository.save(foundProductBox.get());
//            }
//            else {
//                throw new NoSuchElementException("No product box found by given id");
//            }
//        }
//        else {
//            throw new NoSuchElementException("No cart found by given id");
//        }
//    }
//}




// These were in the old ProductService class

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
