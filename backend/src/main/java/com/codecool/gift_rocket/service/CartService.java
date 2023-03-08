package com.codecool.gift_rocket.service;

import com.codecool.gift_rocket.model.Cart;
import com.codecool.gift_rocket.model.CartProduct;
import com.codecool.gift_rocket.model.CartProductId;
import com.codecool.gift_rocket.model.Product;
import com.codecool.gift_rocket.repository.JPA.CartProductRepository;
import com.codecool.gift_rocket.repository.JPA.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CartService {

    CartRepository cartRepository;

    CartProductRepository cartProductRepository;
    ProductService productService;

    @Autowired
    public CartService(CartRepository cartRepository, ProductService productService, CartProductRepository cartProductRepository) {
        this.cartRepository = cartRepository;
        this.productService = productService;
        this.cartProductRepository = cartProductRepository;
    }

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public void addNewCart(Cart newCart) {
        newCart.setTotalPrice(BigDecimal.ZERO);
        cartRepository.save(newCart);
    }

    public Cart findCartById(Long id) {
        return cartRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("No cart found by given id"));
    }

    public void removeCartById(Long cartId) {
        cartRepository.delete(findCartById(cartId));
    }

    public BigDecimal getCartValue(Long cartId) {
        return findCartById(cartId).getTotalPrice();
    }

    public List<Product> getAllProductsInCart(Long cartId) {
        Cart foundCart = findCartById(cartId);
        List<Product> products = new ArrayList<>();
        List<CartProduct> cartProducts = foundCart.getProducts();
        cartProducts.forEach(cartProduct -> products.add(cartProduct.getProduct()));
        return products;
    }

    public void changeProductInCart(Long productId, Long cartId, int quantity) {
        Cart foundCart = findCartById(cartId);
        Product foundProduct = productService.findProductById(productId);
        CartProductId foundCartProductId = new CartProductId(cartId, productId);
        Optional<CartProduct> foundCartProduct = cartProductRepository.findById(foundCartProductId);
        if (foundCartProduct.isPresent()) {
            foundCartProduct.get().changeQuantity(quantity);
            foundCart.setTotalPrice(foundCart.getTotalPrice().
                    add(foundProduct.getPrice().multiply(BigDecimal.valueOf(quantity))));
            cartRepository.save(foundCart);
            cartProductRepository.save(foundCartProduct.get());
            if (foundCartProduct.get().getQuantity() == 0) {
                cartProductRepository.deleteById(foundCartProduct.get().getId());
            }
        } else {
            if (quantity >= 0) {
                CartProduct cartProduct = new CartProduct(foundCart, foundProduct);
                cartProductRepository.save(cartProduct);
                foundCart.setTotalPrice(foundCart.getTotalPrice().
                        add(foundProduct.getPrice().multiply(BigDecimal.valueOf(quantity))));
                cartRepository.save(foundCart);
            }
        }
    }

    public void deleteProductFromCart(Long productId, Long cartId) {
        Cart foundCart = findCartById(cartId);
        Product foundProduct = productService.findProductById(productId);
        CartProductId foundCartProductId = new CartProductId(cartId, productId);
        Optional<CartProduct> foundCartProduct = cartProductRepository.findById(foundCartProductId);
        if (foundCartProduct.isPresent()) {
            foundCart.setTotalPrice(foundCart
                            .getTotalPrice()
                            .subtract(foundProduct
                                    .getPrice()
                                    .multiply(BigDecimal.valueOf(foundCartProduct.get().getQuantity()))));
            cartRepository.save(foundCart);
            cartProductRepository.delete(foundCartProduct.get());
        }
    }
}
