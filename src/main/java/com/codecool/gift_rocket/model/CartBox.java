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
@Table(name = "cart_box")
public class CartBox {
        @EmbeddedId
        private CartBoxId id;

        @ManyToOne(fetch = FetchType.LAZY)
        @MapsId("cartId")
        private Cart cart;

        @ManyToOne(fetch = FetchType.LAZY)
        @MapsId("boxId")
        private Product box;

        @Column(name = "quantity")
        private int quantity = 1;

        public CartBox(Cart cart, Product box) {
            this.cart = cart;
            this.box = box;
            this.id = new CartBoxId(cart.getId(), box.getId());
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartBox cartBox = (CartBox) o;
        return quantity == cartBox.quantity && id.equals(cartBox.id) && Objects.equals(cart, cartBox.cart) && Objects.equals(box, cartBox.box);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cart, box, quantity);
    }
}
