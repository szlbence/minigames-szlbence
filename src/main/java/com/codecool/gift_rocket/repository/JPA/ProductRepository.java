package com.codecool.gift_rocket.repository.JPA;

import com.codecool.gift_rocket.model.Category;
import com.codecool.gift_rocket.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAllByCategory(Category category);
}
