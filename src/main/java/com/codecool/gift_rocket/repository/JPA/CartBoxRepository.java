package com.codecool.gift_rocket.repository.JPA;

import com.codecool.gift_rocket.model.CartBox;
import com.codecool.gift_rocket.model.CartBoxId;
import org.springframework.data.repository.CrudRepository;

public interface CartBoxRepository extends CrudRepository<CartBox, CartBoxId> {
}
