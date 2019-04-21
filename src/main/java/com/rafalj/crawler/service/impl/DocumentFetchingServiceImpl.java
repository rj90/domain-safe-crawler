package com.rafalj.crawler.service.impl;

import com.rafalj.crawler.configuration.CrawlerProperties;
import com.rafalj.crawler.service.DocumentFetchingService;

import java.io.IOException;
import javax.validation.constraints.NotNull;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = {"documents"})
public class DocumentFetchingServiceImpl implements DocumentFetchingService {

  private final CrawlerProperties crawlerProperties;

  public DocumentFetchingServiceImpl(CrawlerProperties crawlerProperties) {
    this.crawlerProperties = crawlerProperties;
  }

  @Override
  @Cacheable(key = "#url")
  public Document fetchDocument(@NotNull String url) throws IOException {
    return Jsoup.connect(url)
        .timeout(crawlerProperties.getTimeout())
        .followRedirects(crawlerProperties.isFollowRedirects())
        .get();
  }
}
