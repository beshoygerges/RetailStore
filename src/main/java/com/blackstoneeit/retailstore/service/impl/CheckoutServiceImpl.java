package com.blackstoneeit.retailstore.service.impl;

import com.blackstoneeit.retailstore.annotation.Loggable;
import com.blackstoneeit.retailstore.dto.CartDto;
import com.blackstoneeit.retailstore.dto.OrderDto;
import com.blackstoneeit.retailstore.entity.Cart;
import com.blackstoneeit.retailstore.entity.Order;
import com.blackstoneeit.retailstore.entity.OrderDetail;
import com.blackstoneeit.retailstore.entity.User;
import com.blackstoneeit.retailstore.repository.CartRepository;
import com.blackstoneeit.retailstore.service.CheckoutService;
import com.blackstoneeit.retailstore.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final CartRepository cartRepository;


    @Loggable
    @Transactional
    @Override
    public OrderDto checkout(CartDto cartDto) {
        Optional<Cart> optionalCart = cartRepository.findById(cartDto.getId());

        Cart cart = optionalCart.orElseThrow(() -> new RuntimeException("Cart not found"));

        Order order = new Order();

        User user = cart.getUser();

        user.addOrder(order);

        calculateOrderDiscount(cart, order);

        calculateOrderDetails(cart, order);

        order.setAfterDiscount(order.getTotal().subtract(order.getDiscount()));

        return MapperUtil.map(order, OrderDto.class);
    }

    private void calculateOrderDiscount(Cart cart, Order order) {
        BigDecimal totalDiscount = DiscountManagementService.apply(cart);

        order.setDiscount(totalDiscount);
    }

    private void calculateOrderDetails(Cart cart, Order order) {
        List<OrderDetail> orderDetails = new ArrayList<>();

        cart.getCartDetails().forEach(cartDetails -> orderDetails.add(new OrderDetail(cartDetails)));

        orderDetails.forEach(order::addOrderDetail);

        order.setTotal(orderDetails
                .stream()
                .map(orderDetail -> orderDetail.getProduct().getPrice().multiply(BigDecimal.valueOf(orderDetail.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add));
    }
}
