package com.blackstoneeit.retailstore.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
public class CartDto implements Serializable {

    @NotNull
    private int id;

    private List<CartDetailsDto> cartDetails;

}
