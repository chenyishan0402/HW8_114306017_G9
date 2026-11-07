# HW8 - 尋知搜尋引擎

## 怎麼執行

先到 [Tavily](https://www.tavily.com/) 申請 API 金鑰，再於 PowerShell 設定：

```powershell
$env:TAVILY_ENABLED = "true"
$env:TAVILY_API_KEY = "tvly-你的金鑰"
.\mvnw.cmd spring-boot:run
```

在瀏覽器開啟 <http://localhost:8080>，輸入關鍵字搜尋。金鑰只存在於目前終端機，不要貼進程式或上傳到 GitHub。

## 程式大致怎麼跑

1. `SearchController` 收到網頁上的關鍵字。
2. `TavilySearchService` 把關鍵字送給 Tavily，拿回標題、連結和摘要。
3. `index.html` 把搜尋結果顯示在頁面上。
