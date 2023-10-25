package com.javagetontop.dto;

import com.javagetontop.model.TransactionStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionDto {

    private Long id;
    private Long userId;
    private BigDecimal amount;
    private BigDecimal fee;
    private TransactionStatus status;
    private LocalDateTime creationDate;


    public TransactionDto() {
    }

    public TransactionDto(Long id, Long userId, BigDecimal amount, BigDecimal fee,
                          TransactionStatus status, LocalDateTime creationDate) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.fee = fee;
        this.status = status;
        this.creationDate = creationDate;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

}
