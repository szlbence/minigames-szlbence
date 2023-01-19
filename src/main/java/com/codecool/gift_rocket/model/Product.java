package com.codecool.gift_rocket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Data
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private BigDecimal price;
    @Column(name = "currency")
    private static final String CURRENCY = "HUF";
    private String name;
    private String description;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_box_id")
    @JsonIgnore
    private ProductBox productBox;
    @Enumerated(EnumType.STRING)
    private Category category;

    public Product(BigDecimal price, String name, String description, Category category) {
        this.price = price;
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return name;
    }
}
