package com.example.topup.controller;

import com.example.topup.model.Product;
import com.example.topup.model.Product.GameCategory;
import com.example.topup.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/user")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String getProducts(@RequestParam("game") String game, Model model) {
        GameCategory category;
        try {
            category = GameCategory.valueOf(game.toUpperCase());
        } catch (IllegalArgumentException e) {
            category = null;
        }
        List<Product> products = (category != null)
            ? productService.getProductsByCategory(category)
            : List.of();
        model.addAttribute("products", products);
        model.addAttribute("game", game);
        return "user/products";
    }
}
