package com.sachin.demobank.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sachin.demobank.Entity.User;

public interface UserRepository extends JpaRepository<User,Long >{

    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    
}
