package com.rafalj.crawler.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
public class MediaElementFetcher implements ElementFetcher {

  @Override
  public Set<String> fetchElements(Element element) {
    Set<String> elements = new HashSet<>();
    for (Element childElement : element.select("[src]")) {
      elements.add(childElement.attr("abs:src"));
    }
    return elements;
  }
}
