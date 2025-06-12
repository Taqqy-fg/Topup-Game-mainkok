package com.example.topup.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import com.example.topup.service.ProductService;
import com.example.topup.model.Product;
import java.util.List;

@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final ProductService productService;

    public UserController(ProductService productService) {
        this.productService = productService;
    }

    // Halaman utama user, tampilkan semua produk
    @GetMapping("/user")
    public String index(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "index";
    }

    // Endpoint untuk menampilkan produk berdasarkan game (MLBB/PUBG)
    @GetMapping("/products/all")
    public String viewAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("game", "All Games");
        return "user/products";
    }
}
