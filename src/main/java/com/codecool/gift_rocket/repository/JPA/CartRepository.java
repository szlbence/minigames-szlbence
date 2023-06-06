package com.codecool.gift_rocket.repository.JPA;

import com.codecool.gift_rocket.model.Cart;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartRepository extends CrudRepository<Cart, Long>{
    @Override
    List<Cart> findAll();
}
