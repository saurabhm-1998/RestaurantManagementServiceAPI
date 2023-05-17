package com.geekster.RestaurantManagementServiceAPI.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInInput {
    private String username;
    private String password;
}
