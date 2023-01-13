package com.codecool.gift_rocket.repository;

import com.codecool.gift_rocket.model.Category;
import com.codecool.gift_rocket.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class ProductMem implements ProductDao{

    private List<Product> products;

    public ProductMem(List<Product> products) {
        this.products = products;
    }


    @Override
    public void add(Product product) {
        products.add(product);
    }

    @Override
    public void remove(UUID id) {
        Product removableProduct = products.stream().filter(product -> product.getId().equals(id)).findAny().get();
        products.remove(removableProduct);

    }

    @Override
    public Product find(UUID id) {
        return products.stream().filter(product -> product.getId().equals(id)).findAny().orElse(null);
    }

    @Override
    public List<Product> getAll() {
       return products;
    }

    @Override
    public List<Product> getByCategory(Category category) {
        return products.stream().filter(product -> product.getCategory().equals(category)).collect(Collectors.toList());
    }
}