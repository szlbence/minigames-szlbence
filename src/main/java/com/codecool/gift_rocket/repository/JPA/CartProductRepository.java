package com.codecool.gift_rocket.repository.JPA;

import com.codecool.gift_rocket.model.CartProduct;
import com.codecool.gift_rocket.model.CartProductId;
import org.springframework.data.repository.CrudRepository;

public interface CartProductRepository extends CrudRepository<CartProduct, CartProductId> {
}
