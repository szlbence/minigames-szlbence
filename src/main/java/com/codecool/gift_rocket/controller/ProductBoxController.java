//package com.codecool.gift_rocket.controller;
//
//import com.codecool.gift_rocket.model.Product;
//import com.codecool.gift_rocket.model.ProductBox;
//import com.codecool.gift_rocket.service.ShopService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.Map;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/productbox")
//@CrossOrigin(origins = "*")
//public class ProductBoxController {
//    @Autowired
//    public ShopService shopService;
//
//    @GetMapping
//    public List<ProductBox> getAllProductBox() {
//        return  shopService.getAllProductBoxes();
//    }
//
//    @GetMapping("/name/{name}")
//    public UUID getProductBoxId(@PathVariable String name) {
//        return shopService.getProductBoxId(name);
//    }
//
//    @PostMapping
//    public void addProductBox(@RequestBody ProductBox productBox) {
//        shopService.addNewProductBox(productBox);
//    }
//
//    @GetMapping("/{id}")
//    public ProductBox findProductBox(@PathVariable UUID id) {
//        return shopService.findProductBox(id);
//    }
//
//    @DeleteMapping("/{id}")
//    public void removeProductBox(@PathVariable UUID id) {
//        shopService.removeProductBox(id);
//    }
//
//    @GetMapping("/{id}/value")
//    public BigDecimal getProductBoxValue(@PathVariable UUID id) {
//        return shopService.getProductBoxValue(id);
//    }
//
//    @GetMapping("/{id}/products")
//    public Map<Product, Integer> getAllProductsInBox(@PathVariable UUID id) {
//        return shopService.getAllProductsInBox(id);
//    }
//
//    @PostMapping("/{id}/add/{productId}")
//    public void addProductToProductBox(@PathVariable UUID id, @PathVariable UUID productId) {
//        shopService.addProductToProductBox(productId, id);
//    }
//
//    @DeleteMapping("/{id}/remove/{productId}")
//    public void removeProductFromProductBox(@PathVariable UUID id, @PathVariable UUID productId) {
//        shopService.removeProductFromProductBox(productId, id);
//    }
//}
