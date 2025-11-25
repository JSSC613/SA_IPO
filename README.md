# TradeIPOSystem - 簡易股票交易與申購系統

這是一個基於 Java Spring Boot 開發的模擬股票交易與申購 (IPO) 系統。
專案重點在於實作**股票抽籤 (Lottery)** 的邏輯，模擬投資人申購、系統扣款、以及管理員執行抽籤的完整流程。

## 功能特色 (Features)

### 管理員 (Administrator)
*   **發布新股**：設定股票名稱、代碼、承銷價、釋出張數與截止時間。
*   **執行抽籤**：當申購期限截止後，可手動觸發系統進行隨機抽籤。
*   **查看狀態**：瀏覽所有股票的申購狀況。

### 投資人 (Investor)
*   **註冊與登入**：建立帳戶並獲得初始模擬資金。
*   **股票申購**：瀏覽進行中的 IPO，系統會自動驗證餘額與申購資格。
*   **資金管理**：申購時自動預扣款項。
*   **查詢結果**：隨時查看申購紀錄與中籤狀態 (中籤/未中籤)。

## 技術架構 (Tech Stack)

*   **後端語言**：Java 17+
*   **核心框架**：Spring Boot 3 (Spring Web)
*   **前端介面**：Thymeleaf Template Engine + Bootstrap 5
*   **資料儲存**：In-Memory (使用 Java List 模擬資料庫，重啟後資料會重置)
*   **開發工具**：VS Code

## 安裝與執行說明 (Installation)

### 前置需求
*   已安裝 Java JDK 17 或以上版本。
*   已安裝 Maven (通常包含在 VS Code Java Extension Pack 中)。

### 執行步驟
1.  **Clone 專案**：
    ```bash
    git clone https://github.com/你的帳號/TradeMaster.git
    ```
2.  **使用 VS Code 開啟資料夾**。
3.  等待 Java Extension 載入專案設定 (右下角會有進度條)。
4.  開啟 `src/main/java/com/example/tradeiposystem/TradeIPOSystemApplication.java`。
5.  點擊上方的 **Run** 按鈕。
6.  當終端機出現 `Started TradeIPOSystemApplication` 後，開啟瀏覽器。
7.  前往網址：[http://localhost:8080](http://localhost:8080)

## 預設測試帳號 (Demo Accounts)

為了方便測試，系統啟動時已預建以下帳號：

| 角色 | 帳號 | 密碼 | 備註 |
| :--- | :--- | :--- | :--- |
| **管理員** | `admin` | `123` | 擁有發布與抽籤權限 |
| **投資人** | (請自行註冊) | (自訂) | 註冊後預設資金為 $100,000 |

## 專案結構簡介
*   `src/main/java/com/example/tradeiposystem/model` - 資料物件 (User, IPOStock, IPORecord)
*   `src/main/java/com/example/tradeiposystem/service` - 業務邏輯 (抽籤演算法、扣款邏輯)
*   `src/main/java/com/example/tradeiposystem/controller` - 網頁路徑控制
*   `src/main/resources/templates` - HTML 網頁檔案

---
