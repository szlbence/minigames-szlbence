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

    @PostMapping("/productbox/addproduct")
    public void addProductToProductBox(@RequestBody Map<String, UUID> ids) {
        shopService.addProductToProductBox(ids.get("productId"), ids.get("boxId"));
    }

    @GetMapping("/productbox/get")
    public List<ProductBox> getAllProductBox() {
        return  shopService.getAllProductBoxes();
    }

    @PostMapping("/productbox/add")
    public void addProductBox(@RequestBody ProductBox productBox) {
        shopService.addNewProductBox(productBox);
    }

    @PostMapping("/productbox/find")
    public ProductBox findProductBox(@RequestBody UUID uuid) {
        return shopService.findProductBox(uuid);
    }

    @DeleteMapping("/productbox/remove")
    public void removeProductBox(@RequestBody UUID uuid) {
        shopService.removeProductBox(uuid);
    }

    @PostMapping("/productbox/products")
    public Map<Product, Integer> getAllProductsInBox(@RequestBody UUID boxID) {
        return shopService.getAllProductsInBox(boxID);
    }

    @PostMapping("/productbox/value")
    public BigDecimal getProductBoxValue(@RequestBody UUID uuid) {
        return shopService.getProductBoxValue(uuid);
    }
}
