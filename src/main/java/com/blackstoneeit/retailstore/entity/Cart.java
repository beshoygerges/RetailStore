package com.blackstoneeit.retailstore.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Cart")
public class Cart extends AbstractEntity {

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinColumn(name = "UserId")
    @OneToOne(optional = false)
    private User user;

    @ToString.Exclude
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartDetail> cartDetails = new ArrayList<>();

    public void addCartDetail(CartDetail cartDetail) {
        this.cartDetails.add(cartDetail);
        cartDetail.setCart(this);
    }

}
