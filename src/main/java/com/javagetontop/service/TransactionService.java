package com.javagetontop.service;

import com.javagetontop.dto.TransactionDto;
import com.javagetontop.model.Transaction;
import com.javagetontop.model.TransactionStatus;
import com.javagetontop.model.UserWallet;
import com.javagetontop.repository.TransactionRepository;
import com.javagetontop.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepo;

    @Autowired
    private WalletRepository walletRepo;

    public TransactionDto createTransaction(TransactionDto transactionDto) {
        UserWallet userWallet = walletRepo.findByUserId(transactionDto.getUserId());
        TransactionDto transactionDto1= new TransactionDto();
        if (userWallet.getBalance().compareTo(transactionDto.getAmount()) < 0) {
            return transactionDto1;
        }

        BigDecimal fee = transactionDto.getAmount().multiply(new BigDecimal("0.10"));
        BigDecimal netAmount = transactionDto.getAmount().subtract(fee);
        userWallet.setBalance(userWallet.getBalance().subtract(netAmount));

        walletRepo.save(userWallet);


        Transaction transaction = new Transaction();
        transaction.setUserId(transactionDto.getUserId());
        transaction.setAmount(netAmount);
        transaction.setFee(fee);
        transaction.setStatus(TransactionStatus.COMPLETED);
        transaction.setCreationDate(LocalDateTime.now());

        Transaction savedTransaction = transactionRepo.save(transaction);

        return transactionDto;
    }
}
