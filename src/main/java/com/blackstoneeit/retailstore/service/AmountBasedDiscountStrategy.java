package com.blackstoneeit.retailstore.service;

import com.blackstoneeit.retailstore.entity.Cart;
import com.blackstoneeit.retailstore.entity.CartDetail;

import java.math.BigDecimal;
import java.util.List;

public interface AmountBasedDiscountStrategy extends DiscountStrategy {

    double getDiscountAmount();

    default BigDecimal apply(Cart cart) {
        return BigDecimal.valueOf(getTotalPrice(cart.getCartDetails()).intValue() / 100 * getDiscountAmount());
    }

    default BigDecimal getTotalPrice(List<CartDetail> cartDetails) {
        return cartDetails.stream()
                .map(cartDetail -> cartDetail.getProduct().getPrice().multiply(BigDecimal.valueOf(cartDetail.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
