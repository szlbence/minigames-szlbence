package com.codecool.gift_rocket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;
@Entity
@NoArgsConstructor
@Data
@Table(name = "product_boxes")
public class ProductBox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "packaging_price")
    private BigDecimal packagingPrice;
    @Column(name = "total_price")
    private BigDecimal totalPrice;
    @Column(name = "currency")
    private static final String CURRENCY = "HUF";
    private String name;
    private String description;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    @JsonIgnore
    private Cart cart;
//    @OneToMany(mappedBy = "productBox", cascade = CascadeType.ALL)
//    private Set<Category> categories;
    @OneToMany(mappedBy = "productBox", cascade = CascadeType.ALL)
    private List<Product> products;

    public ProductBox(BigDecimal packagingPrice, String name, String description) {
        this.packagingPrice = packagingPrice;
        this.totalPrice = packagingPrice;
        this.name = name;
        this.description = description;
//        this.categories = new HashSet<>();
        this.products = new ArrayList<>();
    }


    @Override
    public String toString() {
        return name;
    }

    public void addProduct(Product product) {
        products.add(product);
        product.setProductBox(this);
        totalPrice = totalPrice.add(product.getPrice());
    }

    public void removeProduct(Product product) {
        products.remove(product);
        product.setProductBox(null);
        totalPrice = totalPrice.subtract(product.getPrice());
    }
}
