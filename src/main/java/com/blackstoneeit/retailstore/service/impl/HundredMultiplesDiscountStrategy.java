package com.blackstoneeit.retailstore.service.impl;

import com.blackstoneeit.retailstore.entity.Cart;
import com.blackstoneeit.retailstore.service.AmountBasedDiscountStrategy;

import static com.blackstoneeit.retailstore.constants.ApplicationConstants.HUNDRED_MULTIPLES_DISCOUNT_AMOUNT;

public class HundredMultiplesDiscountStrategy implements AmountBasedDiscountStrategy {

    @Override
    public boolean canApply(Cart cart) {
        return getTotalPrice(cart.getCartDetails()).intValue() / 100 > 0;
    }

    @Override
    public double getDiscountAmount() {
        return HUNDRED_MULTIPLES_DISCOUNT_AMOUNT;
    }
}
