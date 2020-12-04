package com.blackstoneeit.retailstore.service;

import com.blackstoneeit.retailstore.entity.Cart;
import com.blackstoneeit.retailstore.entity.CartDetail;

import java.math.BigDecimal;
import java.util.List;

public interface DiscountStrategy {

    boolean canApply(Cart cart);

    BigDecimal apply(Cart cart);

    BigDecimal getTotalPrice(List<CartDetail> cartDetails);

}
