package com.javagetontop.repository;

import com.javagetontop.model.UserWallet;
import org.springframework.data.repository.CrudRepository;

public interface WalletRepository extends CrudRepository<UserWallet, Integer>
{
    UserWallet findByUserId(Long userId);
}