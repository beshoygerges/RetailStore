package com.blackstoneeit.retailstore.service.impl;

import com.blackstoneeit.retailstore.constants.ApplicationConstants;
import com.blackstoneeit.retailstore.entity.Cart;
import com.blackstoneeit.retailstore.service.PercentageBasedDiscountStrategy;

import static com.blackstoneeit.retailstore.constants.UserType.EMPLOYEE;


public class EmployeeDiscountStrategy implements PercentageBasedDiscountStrategy {

    @Override
    public boolean canApply(Cart cart) {
        return cart.getUser().getType() == EMPLOYEE;
    }

    @Override
    public double getDiscountPercentage() {
        return ApplicationConstants.EMPLOYEE_DISCOUNT_PERCENTAGE;
    }
}
