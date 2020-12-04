package com.blackstoneeit.retailstore.service;

import com.blackstoneeit.retailstore.dto.CartDto;
import com.blackstoneeit.retailstore.dto.OrderDto;

public interface CheckoutService {
    OrderDto checkout(CartDto cartDto);
}
