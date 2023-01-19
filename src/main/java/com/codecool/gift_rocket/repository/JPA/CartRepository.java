package com.codecool.gift_rocket.repository.JPA;

import com.codecool.gift_rocket.model.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, Long>{
}
