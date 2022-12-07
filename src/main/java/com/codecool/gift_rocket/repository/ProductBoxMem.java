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
        ProductBox foundBox = productBoxes.stream().filter(b -> b.getId().equals(boxId)).findAny().orElse(null);
        if(foundBox != null){
            foundBox.addProduct(product);
        }
    }

    @Override
    public void addNewProductBox(ProductBox productBox) {
        productBoxes.add(productBox);
    }

    @Override
    public ProductBox find(UUID boxId) {
        return productBoxes.stream().filter(b -> b.getId().equals(boxId)).findAny().orElse(null);
    }

    @Override
    public void removeProduct(Product product, UUID boxId) {
        ProductBox foundBox = productBoxes.stream().filter(b -> b.getId().equals(boxId)).findAny().orElse(null);
        if(foundBox != null){
            foundBox.removeProduct(product);
        }
    }

    @Override
    public Map<Product, Integer> getAllProductsInProductBox(UUID boxId) {
        ProductBox foundBox = productBoxes.stream().filter(b -> b.getId().equals(boxId)).findAny().orElse(null);
        if(foundBox != null){
            return foundBox.getProducts();
        }
        return null;
    }

    @Override
    public void removeProductBox(UUID boxId) {
        ProductBox foundBox = productBoxes.stream().filter(b -> b.getId().equals(boxId)).findAny().orElse(null);
        if(foundBox != null){
            productBoxes.remove(foundBox);
        }
    }

    @Override
    public BigDecimal getProductBoxValue(UUID boxId) {
        ProductBox foundBox = productBoxes.stream().filter(b -> b.getId().equals(boxId)).findAny().orElse(null);
        if(foundBox != null){
            return foundBox.getTotalPrice();
        }
        return BigDecimal.ZERO;
    }

    @Override
    public List<ProductBox> getAllProductBoxes() {
        return productBoxes;
    }
}
