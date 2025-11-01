package com.sachin.demobank.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sachin.demobank.DTO.RequestDTO.CreateAccountDTO;
import com.sachin.demobank.DTO.RequestDTO.UpdateAccountDTO;
import com.sachin.demobank.DTO.ResponseDTO.AccountResponseDTO;
import com.sachin.demobank.Services.AccountService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountResponseDTO> createAccount(@Valid @RequestBody CreateAccountDTO dto)
    {
        AccountResponseDTO response=accountService.createAccount(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AccountResponseDTO>> getAccountByUserId(@PathVariable Long userId)
    {
        List<AccountResponseDTO> accounts=accountService.getUserAccounts(userId);
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountResponseDTO> getAccountById(@PathVariable Long accountId)
    {
        AccountResponseDTO account=accountService.getAccountsById(accountId);
        return ResponseEntity.ok(account);
    }
    
    @GetMapping("/accountNumber/{accountNumber}")
    public ResponseEntity<AccountResponseDTO> getAccountByAccountNumber(@PathVariable String accountNumber)
    {
        AccountResponseDTO account=accountService.findByAccountNumber(accountNumber);
        return ResponseEntity.ok(account);
    }
    @PutMapping("/{accountId}")
    public ResponseEntity<AccountResponseDTO> updateAccountStatus(@PathVariable Long accountId,@Valid @RequestBody UpdateAccountDTO dto)
    {
        AccountResponseDTO account=accountService.updateAccountStatus(accountId, dto);
        return ResponseEntity.ok(account);
    }


    /*AccountResponseDTO createAccount(CreateAccountDTO dto);
    List <AccountResponseDTO> getUserAccounts(Long userId);
    AccountResponseDTO getAccountsById(Long accountId);
    AccountResponseDTO updateAccountStatus(Long accountId,UpdateAccountDTO dto);
    AccountResponseDTO findByAccountNumber(String AccountNumber); */

}
