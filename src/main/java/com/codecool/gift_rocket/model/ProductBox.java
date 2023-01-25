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

    @OneToMany(
            mappedBy = "box",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
//    todo you may want to remove tranisent
//    @Transient
    @JsonIgnore
    private List<CartBox> boxes; 

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "cart_id")
//    @JsonIgnore
//    private Cart cart;
////    @OneToMany(mappedBy = "productBox", cascade = CascadeType.ALL)
////    private Set<Category> categories;
//    @OneToMany(mappedBy = "productBox", cascade = CascadeType.ALL)
//    private List<Product> products;

    public ProductBox(BigDecimal packagingPrice, String name, String description) {
        this.packagingPrice = packagingPrice;
        this.totalPrice = packagingPrice;
        this.name = name;
        this.description = description;
//        this.categories = new HashSet<>();
        this.boxes = new ArrayList<>();
    }


    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductBox that = (ProductBox) o;
        return id.equals(that.id) && packagingPrice.equals(that.packagingPrice) && totalPrice.equals(that.totalPrice) && name.equals(that.name) && description.equals(that.description) && boxes.equals(that.boxes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, packagingPrice, totalPrice, name, description, boxes);
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
