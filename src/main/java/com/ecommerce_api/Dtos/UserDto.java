package com.ecommerce_api.Dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserDto {
    private String username;

    @Schema(description = "password contain 1 uppercase 1 lowercase 1 special character and at least 6 character length", type = "string") // swagger
    private String password;

    @Schema(description = "ROLE_USER: can access only restaurant api | ROLE_ADMIN:  can access all api", type = "string", example = "ROLE_USER") // swagger
    private String role;

}
