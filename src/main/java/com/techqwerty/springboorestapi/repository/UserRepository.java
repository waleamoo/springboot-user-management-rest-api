package com.techqwerty.springboorestapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techqwerty.springboorestapi.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
    // query method to retrieve a user by email
    Optional<User> findByEmail(String email);
}
