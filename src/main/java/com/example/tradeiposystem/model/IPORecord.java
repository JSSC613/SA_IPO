package com.example.tradeiposystem.model;

import java.time.LocalDateTime;

public class IPORecord {
    // 定義狀態枚舉
    public enum Status { SUCCESS, FAILED_FUNDS, FAILED_EXPIRED, PENDING, WON, LOST }

    private String id;
    private String username;
    private IPOStock stock;
    private Status status;
    private LocalDateTime timestamp;
    private String message;

    // 建構子
    public IPORecord(String id, String username, IPOStock stock, Status status, LocalDateTime timestamp, String message) {
        this.id = id;
        this.username = username;
        this.stock = stock;
        this.status = status;
        this.timestamp = timestamp;
        this.message = message;
    }

    // --- 務必確認有以下 Getter 方法 (因為沒有 Lombok 了) ---

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public IPOStock getStock() { return stock; }
    public void setStock(IPOStock stock) { this.stock = stock; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}