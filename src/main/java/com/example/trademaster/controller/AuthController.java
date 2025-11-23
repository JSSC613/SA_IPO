package com.example.trademaster.controller;

import com.example.trademaster.service.UserService;
import com.example.trademaster.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired private UserService userService;

    @GetMapping("/")
    public String index() { return "redirect:/login"; }

    @GetMapping("/login")
    public String loginPage() { return "login"; }

    @PostMapping("/login")
    public String doLogin(@RequestParam String username, @RequestParam String password, HttpSession session) {
        User user = userService.login(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            if ("ADMIN".equals(user.getRole())) return "redirect:/admin";
            return "redirect:/market"; // 投資人登入後先去行情頁
        }
        return "redirect:/login?error";
    }

    @GetMapping("/register")
    public String registerPage() { return "register"; }

    @PostMapping("/register")
    public String doRegister(@RequestParam String username, @RequestParam String password, @RequestParam double balance) {
        userService.register(username, password, balance);
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}