package com.sachin.demobank.Services;

import java.util.List;

import com.sachin.demobank.DTO.RequestDTO.CreateTransactionDTO;
import com.sachin.demobank.DTO.ResponseDTO.TransactionResponseDTO;

public interface TransactionService {
    TransactionResponseDTO deposit(CreateTransactionDTO dto);
    TransactionResponseDTO withdraw(CreateTransactionDTO dto);
    TransactionResponseDTO transfer(CreateTransactionDTO dto);
    List<TransactionResponseDTO> getTransactionByAccount(String accountNumber);
    

}
