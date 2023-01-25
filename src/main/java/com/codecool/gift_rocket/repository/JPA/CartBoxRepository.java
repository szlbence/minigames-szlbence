package com.codecool.gift_rocket.repository.JPA;

import com.codecool.gift_rocket.model.CartProduct;
import com.codecool.gift_rocket.model.CartBoxId;
import org.springframework.data.repository.CrudRepository;

public interface CartBoxRepository extends CrudRepository<CartProduct, CartBoxId> {
}
