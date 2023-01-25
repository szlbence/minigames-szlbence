package com.codecool.gift_rocket.repository.JPA;

import com.codecool.gift_rocket.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
