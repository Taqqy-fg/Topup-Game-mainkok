package com.example.topup.service;

import com.example.topup.model.Transaction;
import com.example.topup.model.User;
import java.util.List;

public interface TransactionService {
    Transaction saveTransaction(Transaction transaction);
    List<Transaction> getAllTransactions();
    List<Transaction> getTransactionsByUser(User user);
    void deleteTransactionById(Long id);
    void updateTransactionStatus(Long id, Transaction.TransactionStatus status);
    Transaction processPayment(Transaction transaction) throws Exception;
    Transaction createTransaction(User user, 
                                Long productId, 
                                String gameId, 
                                String serverId, 
                                String phoneNumber, 
                                Transaction.PaymentMethod paymentMethod);
}
