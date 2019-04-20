package com.rafalj.crawler.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
public class ImportsElementFetcher implements ElementFetcher {

  @Override
  public Set<String> fetchElements(Element element) {
    Set<String> elements = new HashSet<>();
    for (Element childElement : element.select("link[href]")) {
      elements.add(childElement.attr("abs:href"));
    }
    return elements;
  }
}
