package com.techqwerty.springboorestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techqwerty.springboorestapi.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
}
