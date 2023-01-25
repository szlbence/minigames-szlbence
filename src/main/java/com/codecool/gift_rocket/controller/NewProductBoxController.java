//package com.codecool.gift_rocket.controller;
//
//import com.codecool.gift_rocket.model.Product;
//import com.codecool.gift_rocket.model.ProductBox;
//import com.codecool.gift_rocket.service.ProductBoxService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//
//@RestController
//@RequestMapping("/productbox")
//public class NewProductBoxController {
//    @Autowired
//    public ProductBoxService productBoxService;
//
//    @GetMapping
//    public List<ProductBox> getAllProductBoxes() {
//        return  productBoxService.getAllProductBoxes();
//    }
//
//
//    @PostMapping
//    public void addProductBox(@RequestBody ProductBox productBox) {
//        productBoxService.addNewProductBox(productBox);
//    }
//
//    @GetMapping("/{productBoxId}")
//    public ProductBox findProductBoxById(@PathVariable Long productBoxId) {
//        return productBoxService.findProductBoxById(productBoxId);
//    }
//
//    @DeleteMapping("/{productBoxId}")
//    public void removeProductBoxById(@PathVariable Long productBoxId) {
//        productBoxService.removeProductBoxById(productBoxId);
//    }
//
//    @GetMapping("/{productBoxId}/value")
//    public BigDecimal getProductBoxValue(@PathVariable Long productBoxId) {
//        return productBoxService.getProductBoxValue(productBoxId);
//    }
//
//    @GetMapping("/{productBoxId}/products")
//    public List<Product> getAllProductsInBox(@PathVariable Long productBoxId) {
//        return productBoxService.getAllProductsInBox(productBoxId);
//    }
//
//    @PostMapping("/{productBoxId}/add/{productId}")
//    public void addProductToProductBox(@PathVariable Long productBoxId, @PathVariable Long productId) {
//        productBoxService.addProductToProductBox(productId, productBoxId);
//    }
//
//    @DeleteMapping("/{productBoxId}/remove/{productId}")
//    public void removeProductFromProductBox(@PathVariable Long productBoxId, @PathVariable Long productId) {
//        productBoxService.removeProductFromProductBox(productId, productBoxId);
//    }
//}
