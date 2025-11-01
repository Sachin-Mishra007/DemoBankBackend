package com.sachin.demobank.Services.Impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sachin.demobank.Constants.TransactionStatus;
import com.sachin.demobank.Constants.TransactionType;
import com.sachin.demobank.DTO.Mappers.TransactionMapper;
import com.sachin.demobank.DTO.RequestDTO.CreateTransactionDTO;
import com.sachin.demobank.DTO.ResponseDTO.TransactionResponseDTO;
import com.sachin.demobank.Entity.Account;
import com.sachin.demobank.Entity.Transaction;
import com.sachin.demobank.Exceptions.AccountNotActiveException;
import com.sachin.demobank.Exceptions.InsufficientBalanceException;
import com.sachin.demobank.Repositories.Accountrepository;
import com.sachin.demobank.Repositories.TransactionRepository;
import com.sachin.demobank.Services.TransactionService;
@Service
public class TransactionServiceImpl implements TransactionService{
    private final Accountrepository accountRepository;
    private final TransactionRepository transactionRepository;
    


    public TransactionServiceImpl(Accountrepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }


    @Override
    @Transactional
    public TransactionResponseDTO deposit(CreateTransactionDTO dto) {
        ValidateDto(dto);
        String sourceAccountNumber=dto.getSourceAccount();
        Account sourceAccount=validateAccount(sourceAccountNumber);
        if(dto.getDestinationAccount()!=null)
        {
            throw new IllegalArgumentException("Invalid transaction of DEPOSIT type");
        }
        Transaction tx=TransactionMapper.toEntity(dto, sourceAccount, null);
        tx.setType(TransactionType.DEPOSIT);
        //Update Balance in Source Account
        sourceAccount.setBalance(dto.getAmount().add(sourceAccount.getBalance()));
        sourceAccount=accountRepository.save(sourceAccount);
        //Updating Transaction Status to Success
        tx.setStatus(TransactionStatus.SUCCESS);

        tx=transactionRepository.save(tx);

        return TransactionMapper.toDto(tx);
    }

    @Override
    @Transactional
    public TransactionResponseDTO withdraw(CreateTransactionDTO dto) {

        ValidateDto(dto);
        if(dto.getDestinationAccount()!=null)
        {
            throw new IllegalArgumentException("Invalid transaction of DEPOSIT type");
        }
        Account sourceAccount=validateAccount(dto.getSourceAccount());
        validateAccountBalance(sourceAccount, dto.getAmount());


        //Updating Balance in source Account
        sourceAccount.setBalance(sourceAccount.getBalance().subtract(dto.getAmount()));
        sourceAccount=accountRepository.save(sourceAccount);

        //Updating Transaction Status to Success
        Transaction tx=TransactionMapper.toEntity(dto, sourceAccount, null);
        tx.setType(TransactionType.WITHDRWAL);
        tx=transactionRepository.save(tx);
        return TransactionMapper.toDto(tx);
    }

    @Override
    @Transactional
    public TransactionResponseDTO transfer(CreateTransactionDTO dto) {
        ValidateDto(dto);
        Account sourceAccount=validateAccount(dto.getSourceAccount());
        Account destinationAccount=validateAccount(dto.getDestinationAccount());
        validateAccountBalance(sourceAccount, dto.getAmount());

        //Updating Balance in Source Account
        sourceAccount.setBalance(sourceAccount.getBalance().subtract(dto.getAmount()));
        sourceAccount=accountRepository.save(sourceAccount);

        //Updating Balance in Destination Account
        destinationAccount.setBalance(destinationAccount.getBalance().add(dto.getAmount()));
        destinationAccount=accountRepository.save(destinationAccount);
        //Updating Transaction Status to Success
        Transaction tx=TransactionMapper.toEntity(dto, sourceAccount, destinationAccount);
        tx.setStatus(TransactionStatus.SUCCESS);
        tx.setType(TransactionType.TRANSFER);
        tx=transactionRepository.save(tx);

        return TransactionMapper.toDto(tx);
    }

    @Override
    public List<TransactionResponseDTO> getTransactionByAccount(String accountNumber) {
        if(accountNumber==null)
        {
            throw new IllegalArgumentException("Account number cannot be null");
        }
        Account account=accountRepository.findByAccountNumber(accountNumber).orElseThrow(()->new IllegalArgumentException("Account not found with account number "+accountNumber));
        List<Transaction> debitTransactions=transactionRepository.findBySourceAccount(account);
        List<Transaction> creditTransactions=transactionRepository.findByDestinationAccount(account);
        List<Transaction> allTransaction=new ArrayList<>();
        allTransaction.addAll(debitTransactions);
        allTransaction.addAll(creditTransactions);

        List<TransactionResponseDTO> transactionDTOs=allTransaction.stream().map(TransactionMapper::toDto).sorted((a,b)->(b.getTimestamp().compareTo(a.getTimestamp()))).toList();
        return transactionDTOs;
    }

    private void ValidateDto(CreateTransactionDTO dto)
    {
        if(dto==null)
        {
            throw new IllegalArgumentException("Transaction dto cannot be null");
        }
        if(dto.getSourceAccount()==null)
        {
            throw new IllegalArgumentException("Source Account Cannot be null for a transaction");
        }
        if(dto.getAmount().compareTo(BigDecimal.ZERO)<=0)
        {
            throw new IllegalArgumentException("Ammount should always be greater than 0");
        }
        if(dto.getSourceAccount().equals(dto.getDestinationAccount()))
        {
            throw new IllegalArgumentException("Source and Destination Account Cannot be same");
        }
    }

    private Account validateAccount(String accountNumber)
    {
        Account account=accountRepository.findByAccountNumber(accountNumber).orElseThrow(()->new IllegalArgumentException("No Account found with Account ID "+accountNumber ));
        if(!account.getAccountStatus().toString().equals("ACTIVE"))
        {
            throw new AccountNotActiveException(accountNumber+ " is not Active");
        }
        return account;
    }

    private void validateAccountBalance(Account account, BigDecimal amount)
    {
        if(account.getBalance().compareTo(amount)<0)
        {
            throw new InsufficientBalanceException("Insufficient Balance, Current Balance = "+account.getBalance());
        }
    }

}
