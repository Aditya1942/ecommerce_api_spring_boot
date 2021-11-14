package com.ecommerce_api.Dao;

import java.util.Collection;

import com.ecommerce_api.entities.UserEneity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDao extends JpaRepository<UserEneity, Long> {

    UserEneity findByUsername(String username);

    // @Query("SELECT * FROM UserEneity u")
    // Collection<UserEneity> getSAdmins();

}
