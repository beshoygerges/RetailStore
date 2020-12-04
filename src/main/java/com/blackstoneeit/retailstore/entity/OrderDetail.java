package com.blackstoneeit.retailstore.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "OrderDetail")
public class OrderDetail extends AbstractEntity {

    @JoinColumn(name = "orderId")
    @ManyToOne(optional = false)
    private Order order;

    @JoinColumn(name = "productId")
    @ManyToOne(optional = false)
    private Product product;

    private int quantity;

    public OrderDetail(CartDetail cartDetail) {
        this.product = cartDetail.getProduct();
        this.quantity = cartDetail.getQuantity();
    }
}
