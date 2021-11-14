package com.ecommerce_api.services;

import java.util.List;

import com.ecommerce_api.Dao.UsersDao;
import com.ecommerce_api.Exceptions.NotFound;
import com.ecommerce_api.entities.UserEneity;
import com.ecommerce_api.interfaces.userServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userservice implements userServiceInterface {

    @Autowired
    private UsersDao users;

    @Override
    public List<UserEneity> getAllUsers() {
        return users.findAll();
    }

    @Override
    public UserEneity getUser(Long id) {
        return users.findById(id).orElseThrow(() -> new NotFound("User doesn't exit"));
    }

    @Override
    public List<UserEneity> getAdmin() {
        return users.getAdmin();
    }
}
