package com.blackstoneeit.retailstore.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "CartDetail")
public class CartDetail extends AbstractEntity {

    @JoinColumn(name = "cartId")
    @ManyToOne
    private Cart cart;

    @JoinColumn(name = "productId")
    @ManyToOne
    private Product product;

    private int quantity;
}
