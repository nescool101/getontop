package com.javagetontop.controller;

import com.javagetontop.dto.TransactionDto;
import com.javagetontop.repository.BankAccountRepository;
import com.javagetontop.repository.TransactionRepository;
import com.javagetontop.repository.WalletRepository;
import com.javagetontop.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepo;

    @Autowired
    private WalletRepository walletRepo;

    @Autowired
    private BankAccountRepository bankAccountRepo;

    @Autowired
    private TransactionService transactionService;


    @PostMapping("/transactions")
    public TransactionDto performTransaction(@RequestBody TransactionDto transactionDto) {
        return transactionService.createTransaction(transactionDto);
    }
}
