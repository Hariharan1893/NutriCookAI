package com.nutricookai.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GeminiConfig {

	@Value("${gemini.api-url}")
	private String apiUrl;
	
	@Value("${gemini.api-key}")
	private String apiKey;
	
	@Bean
	public WebClient webClient() {
		return WebClient.builder()
				.baseUrl(apiUrl + "?key=" + apiKey)
				.defaultHeader("Content-Type", "application/json")
				.build();
	}
}
