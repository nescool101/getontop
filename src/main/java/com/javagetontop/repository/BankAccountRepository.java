package com.javagetontop.repository;

import com.javagetontop.model.BankAccount;
import org.springframework.data.repository.CrudRepository;

public interface BankAccountRepository extends CrudRepository<BankAccount, Integer>
{
    BankAccount findByUserId(Long userId);
}
