package com.codecool.gift_rocket.service;

import com.codecool.gift_rocket.model.Cart;
import com.codecool.gift_rocket.model.Role;
import com.codecool.gift_rocket.model.UserEntity;
import com.codecool.gift_rocket.repository.JPA.CartRepository;
import com.codecool.gift_rocket.repository.JPA.UserEntityRepository;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.NameAlreadyBoundException;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserEntityService implements UserDetailsService {
    private final UserEntityRepository userEntityRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public UserEntityService(UserEntityRepository userEntityRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userEntityRepository = userEntityRepository;
        this.passwordEncoder = passwordEncoder;
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
}
