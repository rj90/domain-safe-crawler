package com.rafalj.crawler.service.impl;

import com.rafalj.crawler.configuration.CrawlerProperties;
import com.rafalj.crawler.service.WebCrawlerService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WebCrawlerServiceImpl implements WebCrawlerService {

    private final CrawlerProperties crawlerProperties;

    @Autowired
    public WebCrawlerServiceImpl(CrawlerProperties crawlerProperties) {
        this.crawlerProperties = crawlerProperties;
    }

    @Override
    public void crawl(String url) {
        try {
            final Document document =
                Jsoup.connect(url)
                    .timeout(crawlerProperties.getTimeout())
                    .followRedirects(false)
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
