package com.hcs.autotrading.decide.groq;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "groq")
public record GroqInfo(String url, String apiKey) {
}
