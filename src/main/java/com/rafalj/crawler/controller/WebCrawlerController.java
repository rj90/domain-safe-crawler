package com.rafalj.crawler.controller;

import com.rafalj.crawler.model.PageInfo;
import com.rafalj.crawler.service.WebCrawlerService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class WebCrawlerController {

  private final WebCrawlerService webCrawlerService;

  public WebCrawlerController(WebCrawlerService webCrawlerService) {
    this.webCrawlerService = webCrawlerService;
  }

  @GetMapping
  public PageInfo getWebPageContent(@RequestParam String url) {
    return webCrawlerService.crawl(url);
  }
}
