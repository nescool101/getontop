package com.javagetontop.model;

public enum TransactionStatus {
    INITIATED("Initiated"),
    COMPLETED("Completed"),
    FAILED("Failed");

    private final String description;

    TransactionStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
