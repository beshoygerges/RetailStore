package com.blackstoneeit.retailstore.dto;

import com.blackstoneeit.retailstore.constants.ProductType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@Data
public class ProductDto implements Serializable {

    @NotNull
    private int id;

    private String name;

    private String sku;

    private BigDecimal price;

    private ProductType type;

    private int quantity;
}
