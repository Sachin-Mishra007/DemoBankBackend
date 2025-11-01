package com.sachin.demobank.Services;

import java.util.List;

import com.sachin.demobank.DTO.RequestDTO.CreateAccountDTO;
import com.sachin.demobank.DTO.RequestDTO.UpdateAccountDTO;
import com.sachin.demobank.DTO.ResponseDTO.AccountResponseDTO;

public interface AccountService {
    AccountResponseDTO createAccount(CreateAccountDTO dto);
    List <AccountResponseDTO> getUserAccounts(Long userId);
    AccountResponseDTO getAccountsById(Long accountId);
    AccountResponseDTO updateAccountStatus(Long accountId,UpdateAccountDTO dto);
    AccountResponseDTO findByAccountNumber(String AccountNumber);

}
