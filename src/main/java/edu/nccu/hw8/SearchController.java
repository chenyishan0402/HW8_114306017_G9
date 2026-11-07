package edu.nccu.hw8;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;

@Controller
public class SearchController {
	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

	private final TavilySearchService searchService;

	public SearchController(TavilySearchService searchService) {
		this.searchService = searchService;
	}

	@GetMapping("/")
	public String search(
			@RequestParam(name = "query", required = false) String query,
			Model model) {
		model.addAttribute("query", query == null ? "" : query);
		model.addAttribute("results", List.of());

		if (!StringUtils.hasText(query)) {
			return "index";
		}

		if (!searchService.isConfigured()) {
			model.addAttribute("message", "尚未設定 Tavily API 金鑰，請先參考 README.md。");
			return "index";
		}

		try {
			List<SearchResult> results = searchService.search(query.trim());
			model.addAttribute("results", results);
			if (results.isEmpty()) {
				model.addAttribute("message", "沒有找到結果，請試試其他關鍵字。");
			}
		} catch (RestClientException exception) {
			logger.error("Tavily 搜尋失敗", exception);
			model.addAttribute("message", "搜尋服務目前無法使用，請稍後再試。");
		}

		return "index";
	}
}
