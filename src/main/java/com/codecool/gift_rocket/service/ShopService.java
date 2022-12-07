package com.codecool.gift_rocket.service;

import com.codecool.gift_rocket.model.Product;
import com.codecool.gift_rocket.repository.CartDao;
import com.codecool.gift_rocket.repository.ProductBoxDao;
import com.codecool.gift_rocket.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ShopService {

    private ProductDao productDao;
    private ProductBoxDao productBoxDao;

    private CartDao cartDao;

    @Autowired
    public ShopService(ProductDao productDao, ProductBoxDao productBoxDao, CartDao cartDao){
        this.productDao = productDao;
        this.productBoxDao = productBoxDao;
        this.cartDao = cartDao;
    }

    public void addNewProduct(Product product) {
        productDao.add(product);
    }

    public List<Product> getAllProducts() {
        return productDao.getAll();
    }

    public Product findProduct(UUID id) {
        return productDao.find(id);
    }

    public void removeProduct(UUID id){
        productDao.remove(id);
    }
}
