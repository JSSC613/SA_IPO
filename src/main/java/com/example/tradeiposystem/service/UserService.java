package com.example.tradeiposystem.service;

import com.example.tradeiposystem.model.User;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    // 模擬資料庫
    private Map<String, User> userDb = new HashMap<>();

    public UserService() {
        // 預設一個管理員 (帳號: admin, 密碼: 123)
        userDb.put("admin", new User("admin", "123", "ADMIN", 0));
    }

    public void register(String username, String password, double initialBalance) {
        userDb.put(username, new User(username, password, "INVESTOR", initialBalance));
    }

    public User login(String username, String password) {
        User user = userDb.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
    
    public User getUser(String username) {
        return userDb.get(username);
    }
}
