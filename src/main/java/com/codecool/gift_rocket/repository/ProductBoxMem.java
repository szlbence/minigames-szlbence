package com.codecool.gift_rocket.repository;

import com.codecool.gift_rocket.model.Category;
import com.codecool.gift_rocket.model.Product;
import com.codecool.gift_rocket.model.ProductBox;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class ProductBoxMem implements ProductBoxDao{

    private List<ProductBox> productBoxes = new ArrayList<>();

    @Override
    public void addProduct(Product product, UUID boxId) {

    }

    @Override
    public void addNewProductBox(ProductBox productBox) {

    }

    @Override
    public ProductBox find(UUID boxId) {
        return null;
    }

    @Override
    public void removeProduct(Product product, UUID boxId) {

    }

    @Override
    public Map<Product, Integer> getAllProductsInProductBox(UUID boxId) {
        return null;
    }

    @Override
    public void removeProductBox(UUID boxId) {

    }

    @Override
    public BigDecimal getProductBoxValue(UUID boxId) {
        return null;
    }
}
