package com.rafalj.crawler.service;

import com.rafalj.crawler.model.PageInfo;

public interface WebCrawlerService {

  PageInfo crawl(final String url);
}
