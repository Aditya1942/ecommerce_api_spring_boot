package com.ecommerce_api.interfaces;

import java.util.List;

import com.ecommerce_api.entities.UserEneity;

public interface userServiceInterface {
    List<UserEneity> getAllUsers();

    UserEneity getUser(Long id);

    List<UserEneity> getAdmin();
}
