package com.javagetontop.repository;

import com.javagetontop.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer>{
    List<Transaction> findByUserIdOrderByCreationDateDesc(Long userId);
}
