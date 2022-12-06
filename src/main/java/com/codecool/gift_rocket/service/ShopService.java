package com.codecool.gift_rocket.service;

import com.codecool.gift_rocket.repository.ProductBoxDao;
import com.codecool.gift_rocket.repository.ProductDao;
import com.codecool.gift_rocket.repository.ProductMem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopService {

    private ProductDao productDao;
    private ProductBoxDao productBoxDao;


    @Autowired
    public ShopService(ProductDao productDao, ProductBoxDao productBoxDao){
        this.productDao = productDao;
        this.productBoxDao = productBoxDao;
    }

}
