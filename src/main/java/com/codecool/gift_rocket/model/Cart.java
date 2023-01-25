package com.codecool.gift_rocket.model;

import javax.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;


import java.math.BigDecimal;
import java.util.*;
@Entity
@NoArgsConstructor
@Data
@Getter
@Setter
@NaturalIdCache
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @NaturalId
    private String name;
    @Column(name = "currency")
    private static final String CURRENCY = "HUF";

//    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
//    private List<ProductBox> productBoxes;

    @OneToMany(
            mappedBy = "cart",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<CartBox> carts;

    public Cart(String name) {
        this.carts = new ArrayList<>();
        this.totalPrice = new BigDecimal(0);
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }


//    public void addProductBox(ProductBox productBox) {
//        productBoxes.add(productBox);
//        productBox.setCart(this);
//        totalPrice = totalPrice.add(productBox.getTotalPrice());
//    }
//
//    public void removeLastProductBox(ProductBox productBox) {
//        productBoxes.remove(productBoxes.lastIndexOf(productBox));
//        productBoxes.remove(productBox);
//        productBox.setCart(this);
//        totalPrice = totalPrice.subtract(productBox.getTotalPrice());
//    }
//
//    public void removeProductBox(ProductBox productBox) {
//        productBoxes.removeAll(List.of(productBox));
//        //TODO set all carts to null in productboxes
//        productBox.setCart(null);
//        totalPrice = productBoxes.stream()
//                .map(ProductBox::getTotalPrice)
//                .reduce(BigDecimal::add)
//                .orElse(BigDecimal.ZERO);
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return id.equals(cart.id) && totalPrice.equals(cart.totalPrice) && name.equals(cart.name) && carts.equals(cart.carts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalPrice, name, carts);
    }
}
