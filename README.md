# HW8 - 搜尋引擎
HW8 作業，使用 Spring Boot 串接 **Tavily Search API** 實作自訂搜尋引擎。使用者輸入關鍵字後，系統發送查詢請求至 Tavily API，並在前端頁面展示搜尋結果標題與跳轉超連結，同時具備「查無結果」的提示防呆機制。

---

## 專案架構 (Project Structure)

本專案遵循標準 Spring Boot MVC 架構開發：

```text
HW8_114306017/
├── pom.xml                        # Maven 依賴管理 (Spring Web, Thymeleaf)
├── README.md                      # 專案說明文件
├── src/
│   ├── main/
│   │   ├── java/edu/nccu/hw8/
│   │   │   ├── Hw8114306017Application.java   # Spring Boot 啟動主類別
│   │   │   ├── SearchController.java          # 控制器：處理前端請求與視圖路由
│   │   │   ├── TavilySearchService.java       # 服務層：串接 Tavily API 並解析 JSON
│   │   │   └── SearchResult.java              # Model：封裝搜尋項目（標題、網址）
│   │   └── resources/
│   │       ├── application.properties         # 專案設定檔 (讀取環境變數)
│   │       ├── static/
│   │       │   └── style.css                  # 前端視覺排版樣式
│   │       └── templates/
│   │           └── index.html                 # 前端搜尋介面與結果渲染 (Thymeleaf)
│   └── test/                                  # 單元測試目錄

## 如何下載與測試

### 1. 準備環境

- 安裝 **JDK 25**，並確認 PowerShell 可以執行 `java -version`，顯示版本為 25。
- 安裝 Git。
- 不必另外安裝 Maven；專案附有 Maven Wrapper，第一次執行時會自動下載 Maven 和所需套件。

### 2. 下載專案

在 PowerShell 執行：

```powershell
git clone https://github.com/chenyishan0402/HW8_114306017_G9.git
cd HW8_114306017_G9
```

### 3. 執行測試

```powershell
.\mvnw.cmd clean test
```

第一次執行可能需要一些時間下載 Maven 和套件。看到 `BUILD SUCCESS` 就代表測試通過。

### 4. 啟動網站

```powershell
.\mvnw.cmd spring-boot:run
```

看到 Spring Boot 啟動完成後，在瀏覽器開啟 <http://localhost:8080>。測試完畢後，在執行網站的終端機按 `Ctrl+C` 停止。

## 測試真正的搜尋功能（需要 Tavily API 金鑰）

只啟動網站即可確認頁面能正常開啟；若要取得真正的搜尋結果，每位組員需自行到 [Tavily](https://www.tavily.com/) 申請 API 金鑰，並在啟動網站的同一個 PowerShell 視窗設定：

```powershell
$env:TAVILY_ENABLED = "true"
$env:TAVILY_API_KEY = "tvly-你的金鑰"
.\mvnw.cmd spring-boot:run
```

請將 `tvly-你的金鑰` 換成自己的金鑰。金鑰只存在於目前的終端機工作階段，**不要寫進程式、提交到 GitHub，或分享給其他人**。未設定金鑰時，網站仍可開啟，但搜尋時會提示尚未設定。

## 程式流程

1. `SearchController` 接收搜尋表單的關鍵字。
2. `TavilySearchService` 呼叫 Tavily API 並整理搜尋結果。
3. `index.html` 顯示搜尋結果。
