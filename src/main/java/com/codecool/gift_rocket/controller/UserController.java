package com.codecool.gift_rocket.controller;

import com.codecool.gift_rocket.model.UserEntity;
import com.codecool.gift_rocket.service.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final int REMOVE_PRODUCT_FROM_CpC = -1;
    private final int ADD_PRODUCT_TO_CpC = 1;
    private final UserEntityService userEntityService;


    @PostMapping("/register")
    public UserEntity registerNewUser(@RequestBody UserEntity user) throws Exception {
        return userEntityService.registerUser(user);
    }

    @PostMapping("/login")
    public void loginUser() {
    }

    @GetMapping("/{userName}/coin")
    public BigDecimal getTotalCoin(@PathVariable String userName) {
        return userEntityService.getUsersCoin(userName);
    }

    @PutMapping("/{userName}/coin")
    public void increaseCoinQuantity(@PathVariable String userName) {
        System.out.println("Controller lefut");
        userEntityService.increaseCoinQuantity(userName);
    }

    @GetMapping("/{userName}/cpc")
    public BigDecimal getUserCpC(@PathVariable String userName) {
        return userEntityService.getUsersCpC(userName);
    }


    @PutMapping("/{userName}/add/{productId}")
     public void addCpC(@PathVariable String userName, @PathVariable Long productId) {
        System.out.println("Controller lefut");
        userEntityService.add1OrRemove1CpCFromUpgrade(userName, productId, ADD_PRODUCT_TO_CpC);
    }

    @PutMapping("/{userName}/remove/{productId}")
    public void removeCpC(@PathVariable String userName, @PathVariable Long productId) {
        System.out.println("Controller lefut");
        userEntityService.add1OrRemove1CpCFromUpgrade(userName, productId, REMOVE_PRODUCT_FROM_CpC);
    }

    @DeleteMapping("/{userName}/remove/{productId}")
    public void removeAllCpCForDeletedUpgrades(@PathVariable String userName, @PathVariable Long productId) {
        System.out.println("Controller lefut");
        userEntityService.removeAllCpCForDeletedUpgrades(userName, productId, REMOVE_PRODUCT_FROM_CpC);
    }
}


