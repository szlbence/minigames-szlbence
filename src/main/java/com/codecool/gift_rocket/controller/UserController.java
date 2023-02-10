package com.codecool.gift_rocket.controller;

import com.codecool.gift_rocket.model.AuthenticationDTO;
import com.codecool.gift_rocket.model.UserEntity;
import com.codecool.gift_rocket.service.UserEntityService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {


    private final UserEntityService userEntityService;

    @Autowired
    public UserController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }

    @PostMapping("/register")
    public UserEntity registerNewUser(@RequestBody UserEntity user) throws Exception {
        return userEntityService.registerUser(user);
    }

    @PostMapping("/login")
    public String loginUser() {
        return "Hello";
    }



}
