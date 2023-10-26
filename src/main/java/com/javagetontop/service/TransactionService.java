package com.javagetontop.service;

import com.javagetontop.dto.TransactionDto;
import com.javagetontop.exceptions.InsufficientBalanceException;
import com.javagetontop.exceptions.UserNotFoundException;
import com.javagetontop.model.Transaction;
import com.javagetontop.model.TransactionStatus;
import com.javagetontop.model.UserWallet;
import com.javagetontop.repository.TransactionRepository;
import com.javagetontop.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepo;

    @Autowired
    private WalletRepository walletRepo;

    @Transactional(rollbackFor = Exception.class)
    public TransactionDto createTransaction(TransactionDto transactionDto) throws InsufficientBalanceException {
        UserWallet userWallet = walletRepo.findByUserId(transactionDto.getUserId());

        if (userWallet == null) {
            throw new UserNotFoundException("User not found");
        }

        if (userWallet.getBalance().compareTo(transactionDto.getAmount()) < 0) {
            throw new InsufficientBalanceException("Insufficient balance");
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

        TransactionDto transactionDto1 = new TransactionDto();
        transactionDto1.setId(savedTransaction.getId());
        transactionDto1.setUserId(savedTransaction.getUserId());
        transactionDto1.setAmount(savedTransaction.getAmount());
        transactionDto1.setFee(savedTransaction.getFee());
        transactionDto1.setStatus(savedTransaction.getStatus());
        transactionDto1.setCreationDate(savedTransaction.getCreationDate());

        return transactionDto1;
    }
}
