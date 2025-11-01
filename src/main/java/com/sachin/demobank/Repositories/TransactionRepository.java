package com.sachin.demobank.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sachin.demobank.Entity.Account;
import com.sachin.demobank.Entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Long>{

    List<Transaction> findBySourceAccount(Account sourceAccount);
    List<Transaction> findByDestinationAccount(Account destinationAccount);
}
