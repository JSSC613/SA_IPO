```mermaid
graph LR
    %% 定義角色 (用方括號模擬)
    I[Investor<br/>投資人]
    A[Administrator<br/>管理員]

    %% 定義系統邊界
    subgraph TradeMaster_System [TradeMaster System]
        direction TB
        UC1((Login<br/>登入系統))
        UC2((View Market<br/>瀏覽行情))
        UC3((Apply for Stock<br/>申購股票))
        UC4((Apply for IPO<br/>股票抽籤))
        UC5((Publish IPO<br/>發布新股))
        UC6((Execute Draw<br/>執行抽籤))
    end

    %% 連線
    I --> UC1
    I --> UC2
    I --> UC3
    I --> UC4
    A --> UC1
    A --> UC5
    A --> UC6

    %% 樣式設定 (模擬 Use Case 的樣式)
    style UC4 fill:#f9f,stroke:#333,stroke-width:4px
    style I fill:#fff,stroke:#333
    style A fill:#fff,stroke:#333