package com.example.topup.controller;

import com.example.topup.model.Role; // Import the correct Role enum
import com.example.topup.model.User;
import com.example.topup.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String registerForm() {
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(User user) {
        userService.registerUser(user);
        return "redirect:/auth/login";
    }

    @PostMapping("/register-admin")
    public String registerAdmin(User user) {
        user.setRole(Role.ROLE_ADMIN); // Use the Role enum from the model package
        userService.registerUser(user);
        return "redirect:/auth/login";
    }
}
