package com.blackstoneeit.retailstore.entity;

import com.blackstoneeit.retailstore.constants.UserType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "[User]")
public class User extends AbstractEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String mobileNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType type;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Cart cart;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Order> orders = new LinkedHashSet<>();

    public void addOrder(Order order) {
        this.orders.add(order);
        order.setUser(this);
    }

    public void setCart(Cart cart) {
        this.cart = cart;
        this.cart.setUser(this);
    }
}
