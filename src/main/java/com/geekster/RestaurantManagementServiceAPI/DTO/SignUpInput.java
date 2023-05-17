package com.geekster.RestaurantManagementServiceAPI.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpInput {

    private String username;
    private String password;
    private String email;
}
