package com.codecool.gift_rocket.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class CartBoxId implements Serializable {
        private Long cartId;

        private Long boxId;

    public CartBoxId(Long cartId, Long boxId) {
        this.cartId = cartId;
        this.boxId = boxId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartBoxId that = (CartBoxId) o;
        return cartId.equals(that.cartId) && boxId.equals(that.boxId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, boxId);
    }
}



