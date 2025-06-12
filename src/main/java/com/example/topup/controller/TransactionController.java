package com.example.topup.controller;

import com.example.topup.model.Transaction;
import com.example.topup.model.User;
import com.example.topup.service.ProductService;
import com.example.topup.service.TransactionService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/user/transaction")
public class TransactionController {
    private final TransactionService transactionService;
    private final ProductService productService;

    public TransactionController(TransactionService transactionService, ProductService productService) {
        this.transactionService = transactionService;
        this.productService = productService;
    }

    @PostMapping("/confirm")
    public String confirmTransaction(@AuthenticationPrincipal User user,
                                   @RequestParam Long productId,
                                   @RequestParam String gameId,
                                   @RequestParam String serverId,
                                   @RequestParam String phoneNumber,
                                   @RequestParam Transaction.PaymentMethod paymentMethod) {
        
        transactionService.createTransaction(
            user, productId, gameId, serverId, phoneNumber, paymentMethod);
            
        return "redirect:/user/transaction/history";
    }

    @PostMapping("/process")
    public String processTransaction(@ModelAttribute Transaction transaction, 
                                   @AuthenticationPrincipal User user) {
        try {
            transaction.setUser(user);
            transaction.setTransactionDate(LocalDateTime.now());
            transaction.setStatus(Transaction.TransactionStatus.PENDING);
            
            transactionService.processPayment(transaction);
            
            return "redirect:/user/transaction/success";
        } catch (Exception e) {
            return "redirect:/user/transaction/error";
        }
    }

    @GetMapping("/history")
    public String showTransactionHistory(@AuthenticationPrincipal User user, Model model) {
        List<Transaction> transactions = transactionService.getTransactionsByUser(user);
        model.addAttribute("transactions", transactions);
        return "user/transaction-history";
    }
}
