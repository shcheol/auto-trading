package com.hcs.autotrading.exchange.upbit;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "upbit")
public record UpbitInfo(String url, String path) {
}
