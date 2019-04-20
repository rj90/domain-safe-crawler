package com.rafalj.crawler.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
public class LinksElementFetcher implements ElementFetcher {

  @Override
  public Set<String> fetchElements(Element element) {
    Set<String> urls = new HashSet<>();
    for (Element childElement : element.select("a[href]")) {
      urls.add(childElement.attr("abs:href"));
    }
    return urls;
  }
}
