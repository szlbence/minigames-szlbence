package com.codecool.gift_rocket.repository;

import com.codecool.gift_rocket.model.Category;
import com.codecool.gift_rocket.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductMem implements ProductDao{

    private List<Product> products = new ArrayList<>();


    @Override
    public void add(Product product) {

    }

    @Override
    public void remove(UUID id) {

    }

    @Override
    public Product find(UUID id) {
        return null;
    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public List<Product> getByCategory(Category category) {
        return null;
    }
}
