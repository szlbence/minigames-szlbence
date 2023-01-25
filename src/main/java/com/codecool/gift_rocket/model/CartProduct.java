package com.codecool.gift_rocket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

//@Entity(name = "PostTag")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart_product")
public class CartProduct {
        @EmbeddedId
        private CartBoxId id;

        @ManyToOne(fetch = FetchType.LAZY)
        @MapsId("cartId")
        @JsonIgnore
        private Cart cart;

        @ManyToOne(fetch = FetchType.LAZY)
        @MapsId("productId")
        @JsonIgnore
        private Product product;

    public void changeQuantity(int quantity) {
        this.quantity = this.quantity+quantity;
    }

    @Column(name = "quantity")
        private int quantity = 1;

        public CartProduct(Cart cart, Product product) {
            this.cart = cart;
            this.product = product;
            this.id = new CartBoxId(cart.getId(), product.getId());
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartProduct cartProduct = (CartProduct) o;
        return quantity == cartProduct.quantity && id.equals(cartProduct.id) && Objects.equals(cart, cartProduct.cart) && Objects.equals(product, cartProduct.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cart, product, quantity);
    }
}
