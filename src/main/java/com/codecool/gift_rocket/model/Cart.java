package com.codecool.gift_rocket.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;


import java.math.BigDecimal;
import java.util.*;
@Entity
@NoArgsConstructor
@Data
@NaturalIdCache
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @NaturalId
    private String name;

    @OneToMany(
            mappedBy = "cart",
            cascade = CascadeType.REFRESH,
            orphanRemoval = true
    )
    private List<CartProduct> products;


    public Cart(String name) {
        this.products = new ArrayList<>();
        this.totalPrice = new BigDecimal(0);
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return id.equals(cart.id) && totalPrice.equals(cart.totalPrice) && name.equals(cart.name) && products.equals(cart.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalPrice, name, products);
    }
}
