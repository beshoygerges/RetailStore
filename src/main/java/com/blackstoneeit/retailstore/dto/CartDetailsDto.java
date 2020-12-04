package com.blackstoneeit.retailstore.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CartDetailsDto implements Serializable {

    private ProductDto product;

    private int quantity;
}
