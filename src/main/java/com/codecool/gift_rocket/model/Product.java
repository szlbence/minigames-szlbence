package com.codecool.gift_rocket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "price")
    private BigDecimal price;


    @Column(name = "upgradeprice")
    private BigDecimal upgradePrice;


    @Column(name = "cpc")
    private BigDecimal cpc;
    private String name;
    private String description;
    @Enumerated(value = EnumType.STRING)
    @Column(name="category")
    private Category category;

    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.REFRESH,
            orphanRemoval = true,
            fetch = EAGER
    )
//    todo you may want to remove tranisent
//    @Transient
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonIgnore
    private List<CartProduct> carts;


    public Product(BigDecimal price,BigDecimal upgradePrice,BigDecimal cpc, String name, String description, Category category) {
        this.price = price;
        this.name = name;
        this.description = description;
        this.carts = new ArrayList<>();
        this.category = category;
        this.upgradePrice = upgradePrice;
        this.cpc = cpc;
    }


//    @Override
//    public String toString() {
//        return name;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product that = (Product) o;
        return id.equals(that.id) && price.equals(that.price) && upgradePrice.equals(that.upgradePrice) && cpc.equals(that.cpc) && name.equals(that.name) && description.equals(that.description) && carts.equals(that.carts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, upgradePrice, cpc, name, description, carts);
    }

    //    public void addProduct(Product product) {
//        products.add(product);
//        product.setProductBox(this);
//        totalPrice = totalPrice.add(product.getPrice());
//    }
//
//    public void removeProduct(Product product) {
//        products.remove(product);
//        product.setProductBox(null);
//        totalPrice = totalPrice.subtract(product.getPrice());
//    }
}
