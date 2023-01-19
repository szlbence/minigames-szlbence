package com.codecool.gift_rocket.model;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.util.*;
@Entity
@NoArgsConstructor
@Data
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "total_price")
    private BigDecimal totalPrice;

    private String name;
    @Column(name = "currency")
    private static final String CURRENCY = "HUF";
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<ProductBox> productBoxes;
    public Cart(String name) {
        this.productBoxes = new ArrayList<>();
        this.totalPrice = new BigDecimal(0);
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }


    public void addProductBox(ProductBox productBox) {
        productBoxes.add(productBox);
        productBox.setCart(this);
        totalPrice = totalPrice.add(productBox.getTotalPrice());
    }

    public void removeLastProductBox(ProductBox productBox) {
        productBoxes.remove(productBoxes.lastIndexOf(productBox));
        productBoxes.remove(productBox);
        productBox.setCart(this);
        totalPrice = totalPrice.subtract(productBox.getTotalPrice());
    }

    public void removeProductBox(ProductBox productBox) {
        productBoxes.removeAll(List.of(productBox));
        //TODO set all carts to null in productboxes
        productBox.setCart(null);
        totalPrice = productBoxes.stream()
                .map(ProductBox::getTotalPrice)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }
}
