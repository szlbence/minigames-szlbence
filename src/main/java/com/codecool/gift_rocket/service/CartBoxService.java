package com.codecool.gift_rocket.service;

import com.codecool.gift_rocket.model.Cart;
import com.codecool.gift_rocket.model.CartBox;
import com.codecool.gift_rocket.model.CartBoxId;
import com.codecool.gift_rocket.model.ProductBox;
import com.codecool.gift_rocket.repository.JPA.CartBoxRepository;
import com.codecool.gift_rocket.repository.JPA.CartRepository;
import com.codecool.gift_rocket.repository.JPA.ProductBoxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CartBoxService {
    CartBoxRepository cartBoxRepository;

    CartRepository cartRepository;

    ProductBoxRepository productBoxRepository;

    @Autowired
    public CartBoxService(CartBoxRepository cartBoxRepository, CartRepository cartRepository, ProductBoxRepository productBoxRepository) {
        this.cartBoxRepository = cartBoxRepository;
        this.cartRepository = cartRepository;
        this.productBoxRepository = productBoxRepository;
    }

        public void addProductBoxToCart(Long productBoxId, Long cartId) { //todo get or else throw
        Optional<Cart> foundCart = cartRepository.findById(cartId);
        Optional<ProductBox> foundProductBox = productBoxRepository.findById(productBoxId);

        if(foundCart.isPresent()){
            if(foundProductBox.isPresent()){
                CartBoxId foundCartBoxId = new CartBoxId(cartId, productBoxId);
//                CartBox cartBox = new CartBox(foundCart.get(), foundProductBox.get());
                CartBox foundCartBox = cartBoxRepository.findById(foundCartBoxId).get();
                foundCartBox.setQuantity(20);
                cartBoxRepository.save(foundCartBox);
            }
            else {
                throw new NoSuchElementException("No product box found by given id");
            }
        }
        else {
            throw new NoSuchElementException("No cart found by given id");
        }
    }
}
