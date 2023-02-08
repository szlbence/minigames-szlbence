package com.codecool.gift_rocket.controller;

import com.codecool.gift_rocket.model.AuthenticationDTO;
import com.codecool.gift_rocket.model.UserEntity;
import com.codecool.gift_rocket.service.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {


    private final UserEntityService userEntityService;


    @PostMapping("/register")
    public UserEntity registerNewUser(@RequestBody UserEntity user) throws Exception {
        return userEntityService.registerUser(user);
    }

    @PostMapping("/login")
    public void loginUser() {

    }



}


