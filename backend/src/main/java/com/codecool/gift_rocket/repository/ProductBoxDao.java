package com.codecool.gift_rocket.repository;

import com.codecool.gift_rocket.model.Product;
import com.codecool.gift_rocket.model.ProductBox;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ProductBoxDao {

    void addProduct(Product product, UUID boxId);

    void addNewProductBox(ProductBox productBox);

    ProductBox find(UUID boxId);

    void removeProduct(Product product, UUID boxId);

    Map<Product, Integer> getAllProductsInProductBox(UUID boxId);

    void removeProductBox(UUID boxId);

    BigDecimal getProductBoxValue(UUID boxId);


    List<ProductBox> getAllProductBoxes();

    UUID getBoxByName(String name);
}
