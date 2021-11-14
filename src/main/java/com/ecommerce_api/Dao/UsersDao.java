package com.ecommerce_api.Dao;

import java.util.ArrayList;

import com.ecommerce_api.entities.UserEneity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDao extends JpaRepository<UserEneity, Long>, CrudRepository<UserEneity, Long> {

    UserEneity findByUsername(String username);

    @Query("SELECT u FROM UserEneity u WHERE u.role != 'ROLE_USER'")
    ArrayList<UserEneity> getAdmin();

}
