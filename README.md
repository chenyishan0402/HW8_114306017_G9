# HW8 - 搜尋引擎

使用 Spring Boot、Thymeleaf 和 Tavily Search API 製作的搜尋網站。輸入關鍵字後，頁面會顯示搜尋結果；查無結果時也會顯示提示。

## 開啟/測試

在 PowerShell 切換到專案資料夾。

下載專案：

```powershell
git clone https://github.com/chenyishan0402/HW8_114306017_G9.git
cd HW8_114306017_G9
```

執行測試：

```powershell
.\mvnw.cmd clean test
```

看到 `BUILD SUCCESS` 表示測試通過。啟動網站：

```powershell
.\mvnw.cmd spring-boot:run
```

在瀏覽器開啟 <http://localhost:8080>。結束時回到終端機按 `Ctrl+C`。

## 測試實際搜尋

需要自行申請 [Tavily API 金鑰](https://www.tavily.com/)。在啟動網站的同一個 PowerShell 視窗設定金鑰，再執行啟動指令：

```powershell
$env:TAVILY_ENABLED = "true"
$env:TAVILY_API_KEY = "tvly-你的金鑰"
.\mvnw.cmd spring-boot:run
```

請使用自己的金鑰，勿將金鑰寫入程式或提交到 GitHub。未設定金鑰時，網站仍可開啟，但無法取得實際搜尋結果。
