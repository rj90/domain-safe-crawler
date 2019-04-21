package com.rafalj.crawler.configuration;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "web-crawler")
@Data
@Getter
@Setter
public class CrawlerProperties {
  private boolean followRedirects;
  private int timeout;
}
