package com.hcs.autotrading.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class ApiConfig {

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplateBuilder()
				.setReadTimeout(Duration.ofSeconds(3L))
				.setConnectTimeout(Duration.ofSeconds(3L))
				.build();
	}

	@Bean
	public ObjectMapper objectMapper(){
		return new ObjectMapper();
	}
}
