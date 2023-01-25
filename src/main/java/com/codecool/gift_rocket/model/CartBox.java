package com.codecool.gift_rocket.model;

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
public class CartBox {
        @EmbeddedId
        private CartBoxId id;

        @ManyToOne(fetch = FetchType.LAZY)
        @MapsId("cartId")
        private Cart cart;

        @ManyToOne(fetch = FetchType.LAZY)
        @MapsId("productId")
        private Product product;

    public void changeQuantity(int quantity) {
        this.quantity = this.quantity+quantity;
    }

    @Column(name = "quantity")
        private int quantity = 1;

        public CartBox(Cart cart, Product product) {
            this.cart = cart;
            this.product = product;
            this.id = new CartBoxId(cart.getId(), product.getId());
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartBox cartBox = (CartBox) o;
        return quantity == cartBox.quantity && id.equals(cartBox.id) && Objects.equals(cart, cartBox.cart) && Objects.equals(product, cartBox.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cart, product, quantity);
    }
}
