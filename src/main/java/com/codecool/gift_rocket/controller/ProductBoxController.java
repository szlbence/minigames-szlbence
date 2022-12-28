package com.codecool.gift_rocket.controller;

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
public class ProductBoxController {
    @Autowired
    public ShopService shopService;

    @GetMapping("/productbox")
    public List<ProductBox> getAllProductBox() {
        return  shopService.getAllProductBoxes();
    }

    @PostMapping("/productbox")
    public void addProductBox(@RequestBody ProductBox productBox) {
        shopService.addNewProductBox(productBox);
    }

    @GetMapping("/productbox/{id}")
    public ProductBox findProductBox(@PathVariable UUID id) {
        return shopService.findProductBox(id);
    }

    @DeleteMapping("/productbox/{id}")
    public void removeProductBox(@PathVariable UUID id) {
        shopService.removeProductBox(id);
    }

    @PostMapping("/productbox/{id}/value")
    public BigDecimal getProductBoxValue(@PathVariable UUID id) {
        return shopService.getProductBoxValue(id);
    }

    @PostMapping("/productbox/addproduct")
    public void addProductToProductBox(@RequestBody Map<String, UUID> ids) {
        shopService.addProductToProductBox(ids.get("productId"), ids.get("boxId"));
    }

    @PostMapping("/productbox/products")
    public Map<Product, Integer> getAllProductsInBox(@RequestBody UUID boxID) {
        return shopService.getAllProductsInBox(boxID);
    }
}
