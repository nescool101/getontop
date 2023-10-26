package com.javagetontop.service;

import com.javagetontop.dto.TransactionDto;
import com.javagetontop.exceptions.InsufficientBalanceException;
import com.javagetontop.exceptions.UserNotFoundException;
import com.javagetontop.model.UserWallet;
import com.javagetontop.repository.TransactionRepository;
import com.javagetontop.repository.WalletRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private TransactionRepository transactionRepo;

    @Mock
    private WalletRepository walletRepo;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateTransaction_Success() {
        UserWallet userWallet = new UserWallet();
        userWallet.setUserId(1L);
        userWallet.setBalance(new BigDecimal("100.0"));

        when(walletRepo.findByUserId(1L)).thenReturn(userWallet);
        when(transactionRepo.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setUserId(1L);
        transactionDto.setAmount(new BigDecimal("50.0"));

        TransactionDto result = transactionService.createTransaction(transactionDto);

        assertNotNull(result);
        assertEquals(new BigDecimal("55.000"), userWallet.getBalance());
    }

    @Test
    public void testCreateTransaction_InsufficientBalance() {
        UserWallet userWallet = new UserWallet();
        userWallet.setUserId(1L);
        userWallet.setBalance(new BigDecimal("30.0"));

        when(walletRepo.findByUserId(1L)).thenReturn(userWallet);

        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setUserId(1L);
        transactionDto.setAmount(new BigDecimal("50.0"));

        assertThrows(InsufficientBalanceException.class, () -> {
            transactionService.createTransaction(transactionDto);
        });

        assertEquals(new BigDecimal("30.0"), userWallet.getBalance());
    }

    @Test
    public void testCreateTransaction_UserNotFound() {
        when(walletRepo.findByUserId(1L)).thenReturn(null);

        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setUserId(1L);
        transactionDto.setAmount(new BigDecimal("50.0"));

        assertThrows(UserNotFoundException.class, () -> {
            transactionService.createTransaction(transactionDto);
        });
    }
}
