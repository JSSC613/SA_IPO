package com.example.trademaster.service;

import com.example.trademaster.model.*;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StockService {
    // 這裡改用 final 確保初始化
    private final List<IPOStock> stocks = new ArrayList<>();
    private final List<IPORecord> records = new ArrayList<>();

    public StockService() {
        // 建構子，確保 list 不會是 null
    }

    // 上架股票
    public void publishStock(IPOStock stock, int minutesDuration) {
        if (stock == null) return;
        stock.setId(UUID.randomUUID().toString());
        stock.setDeadline(LocalDateTime.now().plusMinutes(minutesDuration));
        stocks.add(stock);
        System.out.println("股票已發布: " + stock.getStockName());
    }

    // 投資人申購邏輯
    public String apply(User user, String stockId) {
        Optional<IPOStock> stockOpt = stocks.stream().filter(s -> s.getId().equals(stockId)).findFirst();
        if (stockOpt.isEmpty()) return "股票不存在";
        IPOStock stock = stockOpt.get();

        // 1. 檢查重複申購
        boolean hasApplied = records.stream()
                .anyMatch(r -> r.getUsername().equals(user.getUsername()) && 
                               r.getStock().getId().equals(stockId) &&
                               (r.getStatus() == IPORecord.Status.PENDING || r.getStatus() == IPORecord.Status.WON));
        if (hasApplied) return "您已申購過此股票";

        // 2. 建立 ID
        String recordId = UUID.randomUUID().toString();
        
        // 3. 檢查時間
        if (stock.isExpired()) {
            // 使用手動建構子，順序：id, username, stock, status, timestamp, message
            records.add(new IPORecord(recordId, user.getUsername(), stock, IPORecord.Status.FAILED_EXPIRED, LocalDateTime.now(), "申購時間已過"));
            return "失敗：申購時間已過";
        }

        // 4. 檢查餘額
        double cost = stock.getPrice(); 

        if (user.getBalance() < cost) {
            records.add(new IPORecord(recordId, user.getUsername(), stock, IPORecord.Status.FAILED_FUNDS, LocalDateTime.now(), "帳戶餘額不足"));
            return "失敗：餘額不足";
        }

        // 5. 成功扣款並建立紀錄
        user.setBalance(user.getBalance() - cost);
        records.add(new IPORecord(recordId, user.getUsername(), stock, IPORecord.Status.PENDING, LocalDateTime.now(), "申購成功，待抽籤"));
        return "申購成功！已預扣款項: " + cost;
    }

    // 執行抽籤
    public void executeDraw(String stockId) {
        IPOStock stock = stocks.stream().filter(s -> s.getId().equals(stockId)).findFirst().orElse(null);
        if (stock == null) return;

        List<IPORecord> pendingRecords = records.stream()
                .filter(r -> r.getStock().getId().equals(stockId) && r.getStatus() == IPORecord.Status.PENDING)
                .collect(Collectors.toList());

        Collections.shuffle(pendingRecords);

        int winnersCount = stock.getTotalQuantity();

        for (int i = 0; i < pendingRecords.size(); i++) {
            IPORecord record = pendingRecords.get(i);
            if (i < winnersCount) {
                record.setStatus(IPORecord.Status.WON);
                record.setMessage("恭喜中籤！");
            } else {
                record.setStatus(IPORecord.Status.LOST);
                record.setMessage("未中籤，將退款");
            }
        }
    }
    
    public void refundLostUser(User user, double amount) {
        if (user != null) {
            user.setBalance(user.getBalance() + amount);
        }
    }

    // 防呆 Getter
    public List<IPOStock> getAllStocks() { 
        if (stocks == null) return new ArrayList<>();
        return stocks; 
    }
    
    public List<IPORecord> getRecordsByUser(String username) {
        if (username == null) return new ArrayList<>();
        return records.stream().filter(r -> r.getUsername().equals(username)).collect(Collectors.toList());
    }
    
    public List<IPORecord> getRecordsByStock(String stockId) {
        return records.stream().filter(r -> r.getStock().getId().equals(stockId)).collect(Collectors.toList());
    }
}