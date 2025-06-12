package com.example.topup.repository;

import com.example.topup.model.Transaction;
import com.example.topup.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUserOrderByTransactionDateDesc(User user);
    List<Transaction> findAllByOrderByTransactionDateDesc();
    List<Transaction> findByUser(User user);
}
