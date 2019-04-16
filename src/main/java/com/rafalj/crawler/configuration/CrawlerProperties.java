package com.rafalj.crawler.configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "web-crawler")
@Getter
@Setter
@NoArgsConstructor
public class CrawlerProperties {
    private int timeout;
}
