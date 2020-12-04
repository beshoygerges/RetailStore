package com.blackstoneeit.retailstore.service.impl;

import com.blackstoneeit.retailstore.entity.Cart;
import com.blackstoneeit.retailstore.service.PercentageBasedDiscountStrategy;

import static com.blackstoneeit.retailstore.constants.ApplicationConstants.AFFILIATE_DISCOUNT_PERCENTAGE;
import static com.blackstoneeit.retailstore.constants.UserType.AFFILIATE;

public class AffiliateDiscountStrategy implements PercentageBasedDiscountStrategy {

    @Override
    public boolean canApply(Cart cart) {
        return cart.getUser().getType() == AFFILIATE;
    }

    @Override
    public double getDiscountPercentage() {
        return AFFILIATE_DISCOUNT_PERCENTAGE;
    }
}
