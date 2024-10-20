package com.hcs.autotrading.alert.slack;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "slack")
public record SlackInfo(String channel, String token) {
}
