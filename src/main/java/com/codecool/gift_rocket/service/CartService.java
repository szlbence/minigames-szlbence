package com.codecool.gift_rocket.service;

import com.codecool.gift_rocket.model.Cart;
import com.codecool.gift_rocket.model.CartProduct;
import com.codecool.gift_rocket.model.CartProductId;
import com.codecool.gift_rocket.model.Product;
import com.codecool.gift_rocket.repository.JPA.CartProductRepository;
import com.codecool.gift_rocket.repository.JPA.CartRepository;
import com.codecool.gift_rocket.repository.JPA.ProductRepository;
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
    ProductRepository productRepository;

    @Autowired
    public CartService(CartRepository cartRepository, ProductRepository productRepository, CartProductRepository cartProductRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.cartProductRepository = cartProductRepository;
    }

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

    public List<Product> getAllProductsInCart(Long cartId) {
        Optional<Cart> foundCart = cartRepository.findById(cartId);
        if(foundCart.isPresent()){
            List<Product> products = new ArrayList<>();
            List<CartProduct> cartProducts = foundCart.get().getProducts();
            for (CartProduct cartProduct : cartProducts
                 ) {
                products.add(cartProduct.getProduct());
            }
            return products;
        }
        else {
            throw new NoSuchElementException("No cart found by given id");
        }
    }

    public void changeProductInCart(Long productId, Long cartId, int quantity) {
        Optional<Cart> foundCart = cartRepository.findById(cartId);
        Optional<Product> foundProduct = productRepository.findById(productId);

        if(foundCart.isPresent()){
            if(foundProduct.isPresent()){
                CartProductId foundCartProductId = new CartProductId(cartId, productId);
                Optional<CartProduct> foundCartProduct = cartProductRepository.findById(foundCartProductId);
                if(foundCartProduct.isPresent()){
                        foundCartProduct.get().changeQuantity(quantity);
                        foundCart.get().setTotalPrice(foundCart.get().getTotalPrice().
                                add(foundProduct.get().getPrice().multiply(BigDecimal.valueOf(quantity))));
                        cartRepository.save(foundCart.get());
                        cartProductRepository.save(foundCartProduct.get());
                    if (foundCartProduct.get().getQuantity() == 0){
                        cartProductRepository.delete(foundCartProduct.get());
                    }
                }
                else{
                    CartProduct cartProduct = new CartProduct(foundCart.get(), foundProduct.get());
                    cartProductRepository.save(cartProduct);
                    foundCart.get().setTotalPrice(foundCart.get().getTotalPrice().
                            add(foundProduct.get().getPrice().multiply(BigDecimal.valueOf(quantity))));
                    cartRepository.save(foundCart.get());
                }
            }
            else {
                throw new NoSuchElementException("No product box found by given id");
            }
        }
        else {
            throw new NoSuchElementException("No cart found by given id");
        }
    }

    public void deleteProductFromCart(Long productId, Long cartId) {
        Optional<Cart> foundCart = cartRepository.findById(cartId);
        Optional<Product> foundProduct = productRepository.findById(productId);

        if(foundCart.isPresent()){
            if(foundProduct.isPresent()){
                CartProductId foundCartProductId = new CartProductId(cartId, productId);
                Optional<CartProduct> foundCartProduct = cartProductRepository.findById(foundCartProductId);
                if(foundCartProduct.isPresent()){
                        cartProductRepository.delete(foundCartProduct.get());
                        foundCart.get().setTotalPrice(foundCart.get().getTotalPrice().subtract(foundProduct.get()
                                .getPrice().multiply(BigDecimal.valueOf(foundCartProduct.get().getQuantity()))));
                        cartRepository.save(foundCart.get());
                }
            }
            else {
                throw new NoSuchElementException("No product box found by given id");
            }
        }
        else {
            throw new NoSuchElementException("No cart found by given id");
        }
    }

    /*public void deleteProductFromCartIfQtyZero(Long productId, Long cartId) {

        Optional<Cart> foundCart = cartRepository.findById(cartId);
        Optional<Product> foundProductBox = productRepository.findById(productId);

        if (foundCart.isPresent()) {
            if (foundProductBox.isPresent()) {
                CartBoxId foundCartBoxId = new CartBoxId(cartId, productId);
                Optional<CartBox> foundCartBox = cartBoxRepository.findById(foundCartBoxId);
                if(foundCartBox.isPresent()){
                    if (foundCartBox.get().getQuantity() == 0){
                    cartBoxRepository.delete(foundCartBox.get());}
                }


            }
            else { throw new NoSuchElementException("No product box found by given id");
            }
        } else { throw new NoSuchElementException("No cart found by given id");
        }
    }*/


//    public void removeProductBoxFromCart(Long productBoxId, Long cartId) {
//        Optional<Cart> foundCart = cartRepository.findById(cartId);
//        Optional<ProductBox> foundProductBox = productBoxRepository.findById(productBoxId);
//        if(foundCart.isPresent()){
//            if(foundProductBox.isPresent()){
//                foundCart.get().removeProductBox(foundProductBox.get());
//                cartRepository.save(foundCart.get());
//            }
//            else {
//                throw new NoSuchElementException("No product box found by given id");
//            }
//        }
//        else {
//            throw new NoSuchElementException("No cart found by given id");
//        }
//    }
}
