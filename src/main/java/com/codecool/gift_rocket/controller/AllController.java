package com.codecool.gift_rocket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AllController {

//        @Autowired
//        public ShopService shopService;

        @GetMapping
        public void getDogs() {
                System.out.println("hello world");
        }
}
