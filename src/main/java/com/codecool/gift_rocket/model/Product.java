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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "currency")
    private static final String CURRENCY = "HUF";
    private String name;
    private String description;
    @Enumerated(value = EnumType.STRING)
    private Category category;

    @OneToMany(
            mappedBy = "box",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
//    todo you may want to remove tranisent
//    @Transient
    @JsonIgnore
    private List<CartBox> boxes;


    public Product(BigDecimal price, String name, String description, Category category) {
        this.price = price;
        this.name = name;
        this.description = description;
        this.boxes = new ArrayList<>();
        this.category = category;
    }


    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product that = (Product) o;
        return id.equals(that.id) && price.equals(that.price) && name.equals(that.name) && description.equals(that.description) && boxes.equals(that.boxes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, name, description, boxes);
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
