package com.javagetontop.controller;

import com.javagetontop.dto.TransactionDto;
import com.javagetontop.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class TransactionControllerTest {

    @InjectMocks
    private TransactionController transactionController;

    @Mock
    private TransactionService transactionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPerformTransaction() {
        TransactionDto inputTransaction = new TransactionDto();
        inputTransaction.setAmount(BigDecimal.valueOf(100.0));

        TransactionDto mockedResponse = new TransactionDto();
        mockedResponse.setId(1L);

        when(transactionService.createTransaction(inputTransaction)).thenReturn(mockedResponse);

        TransactionDto responseEntity = transactionController.performTransaction(inputTransaction);

        assertNotNull( responseEntity.getId());
        assertEquals(1L, responseEntity.getId());

        verify(transactionService, times(1)).createTransaction(inputTransaction);
    }
}
