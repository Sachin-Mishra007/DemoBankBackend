package com.sachin.demobank.Entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.sachin.demobank.Constants.AccountStatus;
import com.sachin.demobank.Constants.AccountType;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name="accounts")
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false,unique=true)
    private String accountNumber;
    @Column(nullable=false, precision = 15, scale=2)
    private BigDecimal balance;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;
    private LocalDateTime createdAt;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id",nullable=false)
    private User user;
    @OneToMany(mappedBy="destinationAccount" ,cascade=CascadeType.ALL)
    private List<Transaction> creditTransactions;
    @OneToMany(mappedBy="sourceAccount",cascade=CascadeType.ALL)
    private List<Transaction> debitTransactions;
    public Account() {
        this.balance = BigDecimal.ZERO;
        this.accountStatus = AccountStatus.ACTIVE;
        this.createdAt = LocalDateTime.now();
    }

    

}
