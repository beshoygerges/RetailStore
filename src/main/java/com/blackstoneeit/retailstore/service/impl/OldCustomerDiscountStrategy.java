package com.blackstoneeit.retailstore.service.impl;

import com.blackstoneeit.retailstore.entity.Cart;
import com.blackstoneeit.retailstore.service.PercentageBasedDiscountStrategy;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static com.blackstoneeit.retailstore.constants.ApplicationConstants.OLD_CUSTOMER_DISCOUNT_PERCENTAGE;


public class OldCustomerDiscountStrategy implements PercentageBasedDiscountStrategy {

    @Override
    public boolean canApply(Cart cart) {
        return ChronoUnit.DAYS.between(cart.getUser().getCreatedAt(), LocalDateTime.now()) > 2 * 365;
    }

    @Override
    public double getDiscountPercentage() {
        return OLD_CUSTOMER_DISCOUNT_PERCENTAGE;
    }
}
