package com.example.tradeiposystem.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class IPOStock {
    private String id;
    private String stockName;
    private String stockSymbol;
    private double price;
    private int quantityLimit;
    private int totalQuantity;
    private String broker;
    private String marketType;
    private LocalDateTime deadline;

    // --- Getter 和 Setter 開始 (必須在 class 裡面) ---

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getStockName() { return stockName; }
    public void setStockName(String stockName) { this.stockName = stockName; }

    public String getStockSymbol() { return stockSymbol; }
    public void setStockSymbol(String stockSymbol) { this.stockSymbol = stockSymbol; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getQuantityLimit() { return quantityLimit; }
    public void setQuantityLimit(int quantityLimit) { this.quantityLimit = quantityLimit; }

    public int getTotalQuantity() { return totalQuantity; }
    public void setTotalQuantity(int totalQuantity) { this.totalQuantity = totalQuantity; }

    public String getBroker() { return broker; }
    public void setBroker(String broker) { this.broker = broker; }

    public String getMarketType() { return marketType; }
    public void setMarketType(String marketType) { this.marketType = marketType; }

    public LocalDateTime getDeadline() { return deadline; }
    public void setDeadline(LocalDateTime deadline) { this.deadline = deadline; }

    // --- 邏輯方法 (也必須在 class 裡面) ---

    public String getFormattedDeadline() {
        return deadline != null ? deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) : "";
    }
    
    public boolean isExpired() {
        return deadline != null && LocalDateTime.now().isAfter(deadline);
    }
} // <--- 這是 class 的結束括號，所有程式碼都要在它上面