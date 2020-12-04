package com.blackstoneeit.retailstore.service.impl;

import com.blackstoneeit.retailstore.entity.Cart;
import com.blackstoneeit.retailstore.service.AmountBasedDiscountStrategy;
import com.blackstoneeit.retailstore.service.PercentageBasedDiscountStrategy;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;


public class DiscountManagementService {

    private static final List<PercentageBasedDiscountStrategy> percentageStrategies;
    private static final List<AmountBasedDiscountStrategy> amountStrategies;

    static {
        percentageStrategies = asList(new AffiliateDiscountStrategy(), new EmployeeDiscountStrategy(), new OldCustomerDiscountStrategy());
        amountStrategies = Collections.singletonList(new HundredMultiplesDiscountStrategy());
    }

    public static BigDecimal apply(Cart cart) {

        BigDecimal totalDiscount = BigDecimal.ZERO;

        Optional<PercentageBasedDiscountStrategy> percentageStrategy;

        Optional<AmountBasedDiscountStrategy> amountStrategy;

        percentageStrategy = percentageStrategies.stream()
                .filter(s -> s.canApply(cart))
                .findFirst();

        if (percentageStrategy.isPresent()) {
            totalDiscount = totalDiscount.add(percentageStrategy.get().apply(cart));
        }

        amountStrategy = amountStrategies.stream()
                .filter(s -> s.canApply(cart))
                .findFirst();

        if (amountStrategy.isPresent()) {
            totalDiscount = totalDiscount.add(amountStrategy.get().apply(cart));
        }

        return totalDiscount;
    }

}
