package com.example.trademaster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.trademaster.model.IPOStock;
import com.example.trademaster.model.User;
import com.example.trademaster.service.StockService;

import jakarta.servlet.http.HttpSession;

@Controller
public class TradeController {

    @Autowired private StockService stockService;
    // @Autowired private UserService userService; // 如果沒用到可註解掉

    private User getSessionUser(HttpSession session) {
        return (User) session.getAttribute("user");
    }

    // --- 關鍵檢查點：這個方法對應 /investor/ipo ---
    @GetMapping("/investor/ipo")
    public String investorIPO(HttpSession session, Model model) {
        User user = getSessionUser(session);
        if (user == null) return "redirect:/login";
        
        // 加入使用者資訊
        model.addAttribute("user", user);
        
        // 確保這兩個 list 不是 null (StockService 已經修正過防呆，這裡應該安全)
        model.addAttribute("stocks", stockService.getAllStocks());
        model.addAttribute("myRecords", stockService.getRecordsByUser(user.getUsername()));
        
        return "investor_dashboard"; // 對應 templates/investor_dashboard.html
    }

    // 其他方法保持不變...
    @GetMapping("/market")
    public String market(HttpSession session, Model model) {
        User user = getSessionUser(session);
        if (user == null) return "redirect:/login";
        model.addAttribute("user", user);
        return "market";
    }

    @PostMapping("/investor/apply")
    public String applyIPO(@RequestParam String stockId, HttpSession session) {
        User user = getSessionUser(session);
        if (user != null) {
            stockService.apply(user, stockId);
        }
        return "redirect:/investor/ipo";
    }

    @GetMapping("/admin")
    public String adminDashboard(HttpSession session, Model model) {
        User user = getSessionUser(session);
        if (user == null || !"ADMIN".equals(user.getRole())) return "redirect:/login";
        model.addAttribute("stocks", stockService.getAllStocks());
        return "admin_dashboard";
    }
    
    // ... admin methods (publish, draw) ...
    @PostMapping("/admin/publish")
    public String publishStock(IPOStock stock, @RequestParam int duration, HttpSession session) {
        if (getSessionUser(session) != null) stockService.publishStock(stock, duration);
        return "redirect:/admin";
    }
    
    @PostMapping("/admin/draw")
    public String executeDraw(@RequestParam String stockId, HttpSession session) {
        if (getSessionUser(session) != null) stockService.executeDraw(stockId);
        return "redirect:/admin";
    }
}