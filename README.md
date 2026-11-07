# HW8 - 搜尋引擎

## 程式大致怎麼跑

1. `SearchController` 收到網頁上的關鍵字。
2. `TavilySearchService` 把關鍵字送給 Tavily，拿回標題、連結和摘要。
3. `index.html` 把搜尋結果顯示在頁面上。
