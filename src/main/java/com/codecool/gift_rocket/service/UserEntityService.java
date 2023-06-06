package com.codecool.gift_rocket.service;

import com.codecool.gift_rocket.model.*;
import com.codecool.gift_rocket.repository.JPA.CartProductRepository;
import com.codecool.gift_rocket.repository.JPA.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.NameAlreadyBoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserEntityService implements UserDetailsService {
    private final UserEntityRepository userEntityRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    ProductService productService;
    CartService cartService;

    CartProductRepository cartProductRepository;

    @Autowired
    public UserEntityService(UserEntityRepository userEntityRepository, BCryptPasswordEncoder passwordEncoder, ProductService productService, CartService cartService, CartProductRepository cartProductRepository) {
        this.userEntityRepository = userEntityRepository;
        this.passwordEncoder = passwordEncoder;
        this.productService = productService;
        this.cartService = cartService;
        this.cartProductRepository = cartProductRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userEntityRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username is not found!"));
        Collection<SimpleGrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(user.getRole().name()));
        return new User(user.getUsername(), user.getPassword(), roles);
    }

    public UserEntity registerUser(UserEntity userEntity) throws Exception {
        if (userEntityRepository.findByUsername(userEntity.getUsername()).isPresent()) {
            throw new NameAlreadyBoundException("username already exists!");
        } else {
            Cart newCart = new Cart(userEntity.getUsername());
            UserEntity newUser = UserEntity
                    .builder()
                    .cart(newCart)
                    .password(passwordEncoder.encode(userEntity.getPassword()))
                    .role(Role.USER)
                    .username(userEntity.getUsername())
                    .build();
            return userEntityRepository.saveAndFlush(newUser);

        }

    }


    public UserEntity findUserByName(String userName) {
        return userEntityRepository
                .findByUsername(userName)
                .orElseThrow(() -> new NoSuchElementException("No user found by given name"));
    }
    public BigDecimal getUsersCoin(String userName) {
        return findUserByName(userName).getCoin();
    }

    public BigDecimal getUsersCpC(String userName) {
        return findUserByName(userName).getCpc();
    }

    public void add1OrRemove1CpCFromUpgrade(String userName, Long productId, int quantity) {
        UserEntity currentUser = findUserByName(userName);
        Product foundProduct = productService.findProductById(productId);
        currentUser.setCpc(currentUser.getCpc()
                        .add(foundProduct.getCpc()
                        .multiply(BigDecimal.valueOf(quantity))
                        .multiply(currentUser.getMultiplier())
                ));
        userEntityRepository.save(currentUser);
    }

    public void removeAllCpCForDeletedUpgrades(String userName, Long productId, int quantity){
        UserEntity currentUser = findUserByName(userName);
        System.out.println(currentUser);
        Cart userCart = currentUser.getCart();
        System.out.println(userCart);
        Product foundProduct = productService.findProductById(productId);
        System.out.println(foundProduct);
        CartProductId foundCartProductId = new CartProductId(userCart.getId(), productId);
        System.out.println(foundCartProductId);
        System.out.println(foundCartProductId.getProductId());
        System.out.println(foundCartProductId.getCartId());
        Optional<CartProduct> foundCartProduct = cartProductRepository.findById(foundCartProductId);
        System.out.println("Ez nincs meg sztem" + foundCartProduct);
        System.out.println(foundCartProduct);
        if (foundCartProduct.isPresent()) {
            System.out.println("belement az ifbe");
        currentUser.setCpc(currentUser.getCpc()
                        .subtract(foundProduct.getCpc()
                        .multiply(BigDecimal.valueOf(foundCartProduct.get().getQuantity()))
                        .multiply(currentUser.getMultiplier())));
        }
        userEntityRepository.save(currentUser);
    }

    public void increaseCoinQuantity (String userName){
        UserEntity currentUser = findUserByName(userName);
        currentUser.setCoin(currentUser.getCoin()
                .add(currentUser.getCpc()
                .multiply(currentUser.getMultiplier())
                ));
        userEntityRepository.save(currentUser);
    }
}

//test if github import worked
