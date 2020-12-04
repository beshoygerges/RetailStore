package com.blackstoneeit.retailstore.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderDto implements Serializable {

    private int id;

    private BigDecimal total;

    private BigDecimal discount;

    private BigDecimal afterDiscount;

    private UserDto user;

    private List<OrderDetailsDto> orderDetails = new ArrayList<>();
}
