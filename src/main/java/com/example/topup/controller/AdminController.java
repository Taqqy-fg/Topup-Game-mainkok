package com.example.topup.controller;

import com.example.topup.model.Transaction;
import com.example.topup.service.TransactionService;
import com.example.topup.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final TransactionService transactionService;
    private final ProductService productService;

    public AdminController(TransactionService transactionService, ProductService productService) {
        this.transactionService = transactionService;
        this.productService = productService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("transactions", transactionService.getAllTransactions());
        return "admin/dashboard";
    }

    @GetMapping("/products")
    public String manageProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "admin/products";
    }

    @GetMapping("/transactions")
    public String viewTransactions(Model model) {
        model.addAttribute("transactions", transactionService.getAllTransactions());
        return "admin/transactions";
    }

    @PostMapping("/transactions/delete/{id}")
    public String deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransactionById(id);
        return "redirect:/admin/transactions";
    }

    @PostMapping("/transactions/update-status")
    public String updateTransactionStatus(@RequestParam("transactionId") Long id,
                                        @RequestParam("status") Transaction.TransactionStatus status) {
        transactionService.updateTransactionStatus(id, status);
        return "redirect:/admin/transactions";
    }
}
