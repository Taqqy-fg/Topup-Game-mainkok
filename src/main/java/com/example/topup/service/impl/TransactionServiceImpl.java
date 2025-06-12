package com.example.topup.service.impl;

import com.example.topup.model.Transaction;
import com.example.topup.model.Product;
import com.example.topup.model.User;
import com.example.topup.repository.TransactionRepository;
import com.example.topup.repository.ProductRepository;
import com.example.topup.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final ProductRepository productRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                ProductRepository productRepository) {
        this.transactionRepository = transactionRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> getTransactionsByUser(User user) {
        return transactionRepository.findByUser(user);
    }

    @Override
    public void deleteTransactionById(Long id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public void updateTransactionStatus(Long id, Transaction.TransactionStatus status) {
        Transaction transaction = transactionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Transaction not found"));
        transaction.setStatus(status);
        transactionRepository.save(transaction);
    }

    @Override
    @Transactional
    public Transaction processPayment(Transaction transaction) {
        try {
            transaction = saveTransaction(transaction);
            updateTransactionStatus(transaction.getId(), Transaction.TransactionStatus.SUCCESS);
            return transaction;
        } catch (Exception e) {
            throw new RuntimeException("Payment processing failed: " + e.getMessage());
        }
    }

    @Override
    public Transaction createTransaction(User user,
                                      Long productId,
                                      String gameId,
                                      String serverId,
                                      String phoneNumber,
                                      Transaction.PaymentMethod paymentMethod) {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new RuntimeException("Product not found"));

        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setProduct(product);
        transaction.setGameId(gameId);
        transaction.setServerId(serverId);
        transaction.setPhoneNumber(phoneNumber);
        transaction.setPaymentMethod(paymentMethod);
        transaction.setStatus(Transaction.TransactionStatus.PENDING);
        transaction.setAmount(BigDecimal.valueOf(product.getPrice()));
        transaction.setTotalAmount(product.getPrice());

        return transactionRepository.save(transaction);
    }
}