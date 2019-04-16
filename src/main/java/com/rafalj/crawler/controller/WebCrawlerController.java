package com.rafalj.crawler.controller;

import com.rafalj.crawler.service.WebCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class WebCrawlerController {

    private WebCrawlerService webCrawlerService;

    @Autowired
    public WebCrawlerController(WebCrawlerService webCrawlerService) {
        this.webCrawlerService = webCrawlerService;
    }

    @GetMapping
    public void getWebPageContent(@RequestParam String url) {
        webCrawlerService.crawl(url);
    }
}
