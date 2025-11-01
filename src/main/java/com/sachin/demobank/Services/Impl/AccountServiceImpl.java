package com.sachin.demobank.Services.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sachin.demobank.Constants.AccountStatus;
import com.sachin.demobank.DTO.Mappers.AccountMapper;
import com.sachin.demobank.DTO.RequestDTO.CreateAccountDTO;
import com.sachin.demobank.DTO.RequestDTO.UpdateAccountDTO;
import com.sachin.demobank.DTO.ResponseDTO.AccountResponseDTO;
import com.sachin.demobank.Entity.Account;
import com.sachin.demobank.Entity.User;
import com.sachin.demobank.Repositories.Accountrepository;
import com.sachin.demobank.Repositories.UserRepository;
import com.sachin.demobank.Services.AccountService;
import com.sachin.demobank.Utils.AccountNumberGenerator;
@Service
public class AccountServiceImpl implements AccountService{

    private final Accountrepository accountrepository;
    private final UserRepository userRepository;

    

    public AccountServiceImpl(Accountrepository accountrepository, UserRepository userRepository) {
        this.accountrepository = accountrepository;
        this.userRepository = userRepository;
    }

    @Override
    public AccountResponseDTO createAccount(CreateAccountDTO dto) {
        if(dto==null|| dto.getUserId()==null)
        {
            throw new IllegalArgumentException("Create Account DTO or UserId cannot be null");
        }
        User user=userRepository.findById(dto.getUserId()).orElseThrow(
            ()->new IllegalArgumentException("User not found with UserId"+dto.getUserId())
        );

        Account account=new Account();
        account=AccountMapper.toEntity(dto, user);
        account.setAccountNumber(AccountNumberGenerator.generate(dto.getAccountType()));
        Account savedAccount=accountrepository.save(account);
        return AccountMapper.toResponseDTO(savedAccount);
    }

    @Override
    public List<AccountResponseDTO> getUserAccounts(Long userId) {
        if(userId==null) throw new IllegalArgumentException("User Id cannot be null");

        User user=userRepository.findById(userId).orElseThrow(
            ()->
                new IllegalArgumentException("User not found with userId "+userId)
            
        );

        List<Account> accounts=accountrepository.findByUser(user);
        List<AccountResponseDTO> accountDTOs=accounts.stream().map(AccountMapper::toResponseDTO).toList();
        return accountDTOs;
    }

    @Override
    public AccountResponseDTO getAccountsById(Long accountId) {
        if(accountId==null) throw new IllegalArgumentException("Account id cannot be null");

        Account account =accountrepository.findById(accountId).orElseThrow(()->new IllegalArgumentException("Account not found with accountId "+accountId));
        return AccountMapper.toResponseDTO(account);
    }

    @Override
    public AccountResponseDTO updateAccountStatus(Long accountId,UpdateAccountDTO dto) {
        if(accountId==null|| dto==null) throw new IllegalArgumentException("AccountId or Update DTO cannot be null");
        Account account=accountrepository.findById(accountId).orElseThrow(()->new IllegalArgumentException("Account not found for id "+accountId));
        account.setAccountStatus(AccountStatus.valueOf(dto.getStatus()));
        Account updatedAccount=accountrepository.save(account);
        return AccountMapper.toResponseDTO(updatedAccount);
    }

    @Override
    public AccountResponseDTO findByAccountNumber(String AccountNumber) {
        if(AccountNumber==null)
        {
            throw new IllegalArgumentException("Account Id cannot be null");
        }
        Account account=accountrepository.findByAccountNumber(AccountNumber).orElseThrow(()->  new IllegalArgumentException("Account not found for Accouont Number : "+AccountNumber));
        return AccountMapper.toResponseDTO(account);
    }

}
