package com.rafalj.crawler.service.impl;

import java.util.Set;

import org.jsoup.nodes.Element;

public interface ElementFetcher {
  Set<String> fetchElements(Element element);
}
