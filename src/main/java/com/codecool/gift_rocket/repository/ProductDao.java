package com.codecool.gift_rocket.repository;

import com.codecool.gift_rocket.model.Category;
import com.codecool.gift_rocket.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductDao {

    void add(Product product);

    void remove(UUID id);

    Product find(UUID id);

    List<Product> getAll();

    List<Product> getByCategory(Category category);

}
