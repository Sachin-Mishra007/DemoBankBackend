package com.sachin.demobank.DTO.Mappers;

import com.sachin.demobank.DTO.RequestDTO.CreateTransactionDTO;
import com.sachin.demobank.DTO.ResponseDTO.TransactionResponseDTO;
import com.sachin.demobank.Entity.Account;
import com.sachin.demobank.Entity.Transaction;

public class TransactionMapper {

    public static Transaction toEntity(CreateTransactionDTO dto, Account sourceAccount,Account destinationAccount)
    {
        Transaction tx=new Transaction();
        tx.setSourceAccount(sourceAccount);
        tx.setDestinationAccount(destinationAccount);
        tx.setAmount(dto.getAmount());
        tx.setRemarks(dto.getRemarks());
        //We will set Transaction Type from Service Layer
        //tx.setType(TransactionType.valueOf(dto.getTransactionType()));
        return tx;
        
    }

    public static TransactionResponseDTO toDto(Transaction tx)
    {
        return TransactionResponseDTO.builder()
        .id(tx.getId())
        .sourceAccount(tx.getSourceAccount().getAccountNumber())
        .destinationAccount(tx.getDestinationAccount()!=null?tx.getDestinationAccount().getAccountNumber():null)
        .amount(tx.getAmount())
        .remarks(tx.getRemarks())
        .transactionStatus(tx.getStatus().toString())
        .timestamp(tx.getTimestamp())
        .transactionType(tx.getType().toString())
        .build();
    }
}
