
🎲 Monopoly Web Game

這是一個基於 Java Servlet 和 JSP 的大富翁網頁遊戲專案，具備地圖格子、玩家移動、購地、機會與命運卡、背景音效等基本功能。
.
├── src/
│   └── main/
│       ├── java/                # Java 原始碼
│       │   ├── enums/           # 枚舉型別（如格子效果）
│       │   ├── game/
│       │   │   ├── components/  # 遊戲邏輯元件（地圖、玩家等）
│       │   │   └── servlet/     # 處理各種遊戲事件的 Servlet
│       │   └── helper/          # 工具類別
│       └── webapp/
│           ├── META-INF/        # 應用程式資訊
│           ├── WEB-INF/        # Java EE 配置與函式庫
│           ├── js/             # 前端 JS 腳本
│           ├── sounds/         # 遊戲音效
│           ├── images/         # 遊戲圖片資源
│           ├── game.jsp        # 遊戲主畫面
│           └── title.jsp       # 標題畫面
└── build/                      # 編譯後的 class 檔案

⚙️ 安裝與執行方式
1. 安裝依賴環境

    JDK 17+

    Apache Tomcat 10+

2. 專案部署

將專案部署至 Tomcat（例如透過 IntelliJ IDEA 或 Eclipse）：

    將 src/main/webapp 設為 Web Root。

    編譯 src/main/java 下的原始碼。

    確保 WEB-INF/lib 下的 .jar 已正確引用。

    啟動 Tomcat 並在瀏覽器開啟 http://localhost:8080/readme1/title.jsp。

🧩 遊戲功能一覽

    🎲 骰子擲點與玩家移動

    🏘️ 土地購買與過路費判定

    📜 機會與命運卡

    ⛓️ 監獄格與釋放卡

    🔊 音效與背景音樂支援

    🧠 使用多個 Servlet 管理狀態與回合流程

📝 設計原則

    無 MVC 框架：採用直接 Servlet + JSP 架構，保持簡潔。

    資料夾依功能分層：如 components, servlet, helper，維持一致性與可維護性。

    遊戲邏輯集中於 game.components，Servlet 負責處理請求與回應。
