package com.topup.controller;

import com.example.topup.model.Product;
import com.example.topup.model.Product.GameCategory;
import com.example.topup.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String showProducts(@RequestParam(value = "game", required = false) String game, Model model) {
        List<Product> products;
        String gameDisplayName = "All Games";
        if (game != null) {
            try {
                GameCategory category = GameCategory.valueOf(game.toUpperCase());
                products = productService.getProductsByCategory(category);
                gameDisplayName = category.name();
            } catch (Exception e) {
                products = productService.getAllProducts();
            }
        } else {
            products = productService.getAllProducts();
        }
        model.addAttribute("products", products);
        model.addAttribute("game", gameDisplayName);
        return "user/products";
    }
}
