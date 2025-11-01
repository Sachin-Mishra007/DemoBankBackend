package com.sachin.demobank.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sachin.demobank.Entity.Account;
import com.sachin.demobank.Entity.User;

public interface Accountrepository extends JpaRepository<Account,Long> {
    Optional<Account> findByAccountNumber(String accountNumber);
    List<Account> findByUser(User user);

}
