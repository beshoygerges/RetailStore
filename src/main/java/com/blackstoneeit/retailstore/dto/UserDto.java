package com.blackstoneeit.retailstore.dto;


import com.blackstoneeit.retailstore.constants.UserType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class UserDto implements Serializable {

    private int id;

    private String name;

    private String mobileNumber;

    private UserType type;
}
