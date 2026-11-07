# HW8 - 搜尋引擎

這是一個使用 Java、Spring Boot 和 Thymeleaf 製作的搜尋網站。輸入關鍵字後，程式會呼叫 Tavily Search API，並顯示搜尋結果的標題、網址和摘要。

## 組員如何下載與測試

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
