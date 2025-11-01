package com.sachin.demobank.Entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.sachin.demobank.Constants.TransactionStatus;
import com.sachin.demobank.Constants.TransactionType;

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
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Column(nullable=false, precision=15,scale=2)
    private BigDecimal amount;
    private LocalDateTime timestamp;
    private String remarks;

    @Column(nullable =false)
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="source_account_id")
    private Account sourceAccount;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="destination_account_id")
    private Account destinationAccount;

    public Transaction() {
        this.timestamp = LocalDateTime.now();
        this.status = TransactionStatus.INPROGRESS;
    }

    //Constructor for deposit and withdrawal
    public Transaction(TransactionType type, BigDecimal amount , Account sourceAccount, String remarks)
    {
        this();
        this.type=type;
        this.sourceAccount=sourceAccount;
        this.amount=amount;
        this.remarks=remarks;
    }

    //Constructor for Transfer
    public Transaction(TransactionType type, BigDecimal amount,Account sourceAccount, Account destinationAccount, String remarks)
    {
        this(type,amount,sourceAccount,remarks);
        this.destinationAccount=destinationAccount;
    }


}
