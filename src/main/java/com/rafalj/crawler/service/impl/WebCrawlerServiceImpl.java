package com.rafalj.crawler.service.impl;

import com.rafalj.crawler.model.PageInfo;
import com.rafalj.crawler.service.DocumentFetchingService;
import com.rafalj.crawler.service.WebCrawlerService;

import java.io.IOException;
import java.net.URI;
import java.time.Duration;
import java.time.Instant;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.constraints.NotNull;

import lombok.extern.slf4j.Slf4j;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Service
@Slf4j
public class WebCrawlerServiceImpl implements WebCrawlerService {

  private final DocumentFetchingService documentFetchingService;
  private final ElementFetcher importsElementFetcher;
  private final ElementFetcher linksElementFetcher;
  private final ElementFetcher mediaElementFetcher;

  public WebCrawlerServiceImpl(
      DocumentFetchingService documentFetchingService,
      ElementFetcher importsElementFetcher,
      ElementFetcher linksElementFetcher,
      ElementFetcher mediaElementFetcher
  ) {
    this.documentFetchingService = documentFetchingService;
    this.importsElementFetcher = importsElementFetcher;
    this.linksElementFetcher = linksElementFetcher;
    this.mediaElementFetcher = mediaElementFetcher;
  }

  @Override
  public PageInfo crawl(final String url) {
    log.info("Starting crawler");
    Instant start = Instant.now();
    PageInfo pageInfo = crawlUrl(url, null);
    Instant end = Instant.now();
    log.info("Site crawled in {} seconds", Duration.between(start, end));
    return pageInfo;
  }

  private PageInfo crawlUrl(final String url, final Map<String, PageInfo> visitedDocuments) {
    try {
      log.info("Starting crawler for url {}", url);
      if (visitedDocuments != null && visitedDocuments.containsKey(url)) {
        return getExistingPageInfo(visitedDocuments.get(url));
      } else {
        return prepareNewPageInfo(visitedDocuments, url);
      }
    } catch (IOException e) {
      log.error("Exception occurred", e);
      return null;
    }
  }

  private PageInfo getExistingPageInfo(PageInfo pageInfo) {
    log.info("Already visited. Preparing page data");
    return PageInfo
            .builder()
            .title(pageInfo.getTitle())
            .url(pageInfo.getUrl())
            .build();
  }

  private PageInfo prepareNewPageInfo(
      Map<String, PageInfo> visitedDocuments,
      @NotNull String url
  ) throws IOException {
    log.info("Preparing page data for new page");
    final Document document = documentFetchingService.fetchDocument(url);
    PageInfo build = PageInfo
        .builder()
        .title(document.title())
        .url(url)
        .build();
    final Map<String, PageInfo> newVisitedDocuments =
            Optional.ofNullable(visitedDocuments).orElse(new HashMap<>());
    newVisitedDocuments.put(url, build);
    build.setElements(
        getWebPageElementUrls(document).entrySet().stream()
          .flatMap(element ->
            prepareElements(newVisitedDocuments, element,
              new DefaultUriBuilderFactory(url).builder().build())
          )
          .collect(Collectors.toList())
    );
    log.info("Found {} elements on {}", build.getElements().size(), url);
    return build;
  }

  private Map<ElementType, Set<String>> getWebPageElementUrls(Document document) {
    Map<ElementType, Set<String>> elements = new EnumMap<>(ElementType.class);
    elements.put(ElementType.IMPORT, importsElementFetcher.fetchElements(document));
    elements.put(ElementType.LINK, linksElementFetcher.fetchElements(document));
    elements.put(ElementType.MEDIA, mediaElementFetcher.fetchElements(document));
    log.info("Found {} imports, {} links and {} media",
        elements.get(ElementType.IMPORT).size(),
        elements.get(ElementType.LINK).size(),
        elements.get(ElementType.MEDIA).size());
    return elements;
  }

  private Stream<PageInfo> prepareElements(
      Map<String, PageInfo> visitedDocuments,
      @NotNull Map.Entry<ElementType, Set<String>> pageElementEntry,
      URI parentUri
  ) {
    switch (pageElementEntry.getKey()) {
      case IMPORT:
      case MEDIA:
        return pageElementEntry.getValue().stream()
          .map(element -> PageInfo.builder().url(element).build());
      case LINK:
        return pageElementEntry.getValue().parallelStream()
          .map(
            url ->
              isSameDomain(parentUri, url)
              ? crawlUrl(url, visitedDocuments)
              : PageInfo.builder().url(url).build()
          );
      default:
        return Stream.of();
    }
  }

  private boolean isSameDomain(@NotNull URI parentUri, String url) {
    if (Objects.equals(
        parentUri.getHost(),
        new DefaultUriBuilderFactory(url).builder().build().getHost()
    )) {
      log.info("Uri {} belongs to the same domain. Will be followed.", url);
      return true;
    } else {
      log.info("Uri {} belongs to the different domain. Skipping.", url);
      return false;
    }
  }
}
