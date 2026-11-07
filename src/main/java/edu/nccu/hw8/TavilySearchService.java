package edu.nccu.hw8;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TavilySearchService {
	private final boolean enabled;
	private final String apiKey;
	private final RestTemplate restTemplate = new RestTemplate();

	public TavilySearchService(
			@Value("${tavily.enabled:false}") boolean enabled,
			@Value("${tavily.api-key:}") String apiKey) {
		this.enabled = enabled;
		this.apiKey = apiKey;
	}

	public boolean isConfigured() {
		return enabled && !apiKey.isBlank();
	}

	public List<SearchResult> search(String query) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(apiKey);

		Map<String, Object> body = Map.of("query", query, "max_results", 10);
		HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
		Map<String, Object> response = restTemplate.postForObject(
				"https://api.tavily.com/search", request, Map.class);

		List<SearchResult> results = new ArrayList<>();
		if (response != null && response.get("results") instanceof List<?> items) {
			for (Object item : items) {
				if (item instanceof Map<?, ?> fields
						&& fields.get("title") != null
						&& fields.get("url") != null) {
					results.add(new SearchResult(
							fields.get("title").toString(),
							fields.get("url").toString(),
							fields.get("content") == null ? "" : fields.get("content").toString()));
				}
			}
		}
		return results;
	}
}
