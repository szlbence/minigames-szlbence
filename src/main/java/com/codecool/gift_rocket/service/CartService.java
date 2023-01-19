package com.codecool.gift_rocket.service;

import com.codecool.gift_rocket.model.Cart;
import com.codecool.gift_rocket.model.ProductBox;
import com.codecool.gift_rocket.repository.JPA.CartRepository;
import com.codecool.gift_rocket.repository.JPA.ProductBoxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductBoxRepository productBoxRepository;
    public List<Cart> getAllCarts() {
        return (List<Cart>) cartRepository.findAll();
    }

    public void addNewCart(Cart newCart) {
        newCart.setTotalPrice(BigDecimal.ZERO);
        cartRepository.save(newCart);
    }

    public Cart findCartById(Long id) {
        Optional<Cart> foundCart = cartRepository.findById(id);
        if(foundCart.isPresent()){
            return foundCart.get();
        }
        else {
            throw new NoSuchElementException("No cart found by given id");
        }
    }

    public void removeCartById(Long id) {
        Optional<Cart> foundCart = cartRepository.findById(id);
        if(foundCart.isPresent()){
            cartRepository.delete(foundCart.get());
        }
        else {
            throw new NoSuchElementException("No cart found by given id");
        }
    }

    public BigDecimal getCartValue(Long cartId) {
        Optional<Cart> foundCart = cartRepository.findById(cartId);
        if(foundCart.isPresent()){
            return foundCart.get().getTotalPrice();
        }
        else {
            throw new NoSuchElementException("No cart found by given id");
        }
    }

    public List<ProductBox> getAllProductBoxesInCart(Long cartId) {
        Optional<Cart> foundCart = cartRepository.findById(cartId);
        if(foundCart.isPresent()){
            return foundCart.get().getProductBoxes();
        }
        else {
            throw new NoSuchElementException("No cart found by given id");
        }
    }

    public void addProductBoxToCart(Long productBoxId, Long cartId) {
        Optional<Cart> foundCart = cartRepository.findById(cartId);
        Optional<ProductBox> foundProductBox = productBoxRepository.findById(productBoxId);
        if(foundCart.isPresent()){
            if(foundProductBox.isPresent()){
                foundCart.get().addProductBox(foundProductBox.get());
                cartRepository.save(foundCart.get());
            }
            else {
                throw new NoSuchElementException("No product box found by given id");
            }
        }
        else {
            throw new NoSuchElementException("No cart found by given id");
        }
    }

    public void removeLastProductBoxFromCart(Long productBoxId, Long cartId) {
        Optional<Cart> foundCart = cartRepository.findById(cartId);
        Optional<ProductBox> foundProductBox = productBoxRepository.findById(productBoxId);
        if(foundCart.isPresent()){
            if(foundProductBox.isPresent()){
                foundCart.get().removeLastProductBox(foundProductBox.get());
                cartRepository.save(foundCart.get());
            }
            else {
                throw new NoSuchElementException("No product box found by given id");
            }
        }
        else {
            throw new NoSuchElementException("No cart found by given id");
        }
    }

    public void removeProductBoxFromCart(Long productBoxId, Long cartId) {
        Optional<Cart> foundCart = cartRepository.findById(cartId);
        Optional<ProductBox> foundProductBox = productBoxRepository.findById(productBoxId);
        if(foundCart.isPresent()){
            if(foundProductBox.isPresent()){
                foundCart.get().removeProductBox(foundProductBox.get());
                cartRepository.save(foundCart.get());
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
