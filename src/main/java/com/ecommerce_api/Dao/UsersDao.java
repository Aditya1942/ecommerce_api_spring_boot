package com.ecommerce_api.Dao;

import com.ecommerce_api.entities.UserEneity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDao extends JpaRepository<UserEneity, Long> {

    UserEneity findByUsername(String username);

}
