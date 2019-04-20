package com.rafalj.crawler.service;

import java.io.IOException;

import javax.validation.constraints.NotNull;

import org.jsoup.nodes.Document;

public interface DocumentFetchingService {

  Document fetchDocument(@NotNull String url) throws IOException;
}
