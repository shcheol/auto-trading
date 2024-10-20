package com.hcs.autotrading.exchange.upbit;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "upbit")
public record UpbitInfo(String url, Map<String, String> path) {
}
