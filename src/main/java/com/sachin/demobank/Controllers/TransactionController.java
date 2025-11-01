package com.sachin.demobank.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sachin.demobank.DTO.RequestDTO.CreateTransactionDTO;
import com.sachin.demobank.DTO.ResponseDTO.TransactionResponseDTO;
import com.sachin.demobank.Services.TransactionService;

import jakarta.validation.Valid;
@RestController("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    /*
     * TransactionResponseDTO deposit(CreateTransactionDTO dto);
    TransactionResponseDTO withdraw(CreateTransactionDTO dto);
    TransactionResponseDTO transfer(CreateTransactionDTO dto);
    List<TransactionResponseDTO> getTransactionByAccount(String accountNumber);
     */

     @PostMapping("/deposit")
     ResponseEntity<TransactionResponseDTO> deposit(@Valid @RequestBody CreateTransactionDTO dto)
     {
        TransactionResponseDTO response=transactionService.deposit(dto);
        return ResponseEntity.ok(response);
     }
     @PostMapping("/withdraw")
     ResponseEntity<TransactionResponseDTO> withdraw(@Valid @RequestBody CreateTransactionDTO dto)
     {
        TransactionResponseDTO response=transactionService.withdraw(dto);
        return ResponseEntity.ok(response);
     }
     @PostMapping("/transfer")
     ResponseEntity<TransactionResponseDTO> transfer(@Valid @RequestBody CreateTransactionDTO dto)
     {
        TransactionResponseDTO response=transactionService.transfer(dto);
        return ResponseEntity.ok(response);
     }

     @GetMapping("/account/{accountNumber}")
     ResponseEntity<List<TransactionResponseDTO>> getTransactionsByAccountNumber(@PathVariable String accountNumber)
     {
        List<TransactionResponseDTO> transactions=transactionService.getTransactionByAccount(accountNumber);
        return ResponseEntity.ok(transactions);
     }


}
