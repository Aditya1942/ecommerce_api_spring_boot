package com.ecommerce_api.Dtos;

import lombok.Data;

@Data
public class UserUpdateDto {
    private String username;
    private String password;
    private String confirmPassword;
    private String role;

}
