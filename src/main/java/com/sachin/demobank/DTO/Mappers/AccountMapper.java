package com.sachin.demobank.DTO.Mappers;

import com.sachin.demobank.Constants.AccountStatus;
import com.sachin.demobank.Constants.AccountType;
import com.sachin.demobank.DTO.RequestDTO.CreateAccountDTO;
import com.sachin.demobank.DTO.RequestDTO.UpdateAccountDTO;
import com.sachin.demobank.DTO.ResponseDTO.AccountResponseDTO;
import com.sachin.demobank.Entity.Account;
import com.sachin.demobank.Entity.User;

public class AccountMapper {
    public static Account toEntity(CreateAccountDTO dto,User user)
    {
        Account account=new Account();
        account.setAccountType(AccountType.valueOf(dto.getAccountType()));
        account.setBalance(dto.getInitalBalance());
        account.setUser(user);
        return account;
        
    }
    public static void updateAccount(Account account,UpdateAccountDTO dto)
    {
        account.setAccountStatus(AccountStatus.valueOf(dto.getStatus()));
    }
    public static AccountResponseDTO toResponseDTO(Account account)
    {
        return AccountResponseDTO.builder()
            .id(account.getId())
            .accountNuber(account.getAccountNumber())
            .accountStatus(account.getAccountStatus().toString())
            .accountType(account.getAccountType().toString())
            .balance(account.getBalance())
            .userId(account.getUser().getId())
            .build();
    
    }

}
