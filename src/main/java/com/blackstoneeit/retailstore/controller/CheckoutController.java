package com.blackstoneeit.retailstore.controller;

import com.blackstoneeit.retailstore.dto.CartDto;
import com.blackstoneeit.retailstore.dto.OrderDto;
import com.blackstoneeit.retailstore.service.CheckoutService;
import com.blackstoneeit.retailstore.util.MapperUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("rest/v1/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;

    @PostMapping
    public OrderDto checkout(@Valid @RequestBody final CartDto cart) {
        return MapperUtil.map(checkoutService.checkout(cart), OrderDto.class);
    }
}
