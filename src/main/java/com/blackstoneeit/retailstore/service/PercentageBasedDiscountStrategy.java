package com.blackstoneeit.retailstore.service;

import com.blackstoneeit.retailstore.constants.ProductType;
import com.blackstoneeit.retailstore.entity.Cart;
import com.blackstoneeit.retailstore.entity.CartDetail;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public interface PercentageBasedDiscountStrategy extends DiscountStrategy {

    List<ProductType> EXCLUDED_TYPES = Collections.singletonList(ProductType.GROCERIES);

    double getDiscountPercentage();

    default BigDecimal apply(Cart cart) {
        return getTotalPrice(cart.getCartDetails()).multiply(BigDecimal.valueOf(getDiscountPercentage()));
    }

    default BigDecimal getTotalPrice(List<CartDetail> cartDetails) {

        return cartDetails.stream()
                .filter(cartDetail -> !EXCLUDED_TYPES.contains(cartDetail.getProduct().getType()))
                .map(cartDetail -> cartDetail.getProduct().getPrice().multiply(BigDecimal.valueOf(cartDetail.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
