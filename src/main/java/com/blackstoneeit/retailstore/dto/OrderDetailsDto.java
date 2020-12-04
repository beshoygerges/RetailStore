package com.blackstoneeit.retailstore.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class OrderDetailsDto implements Serializable {

    private ProductDto product;

    private int quantity;
}
