package com.rafalj.crawler.service

import com.google.common.io.Resources
import com.rafalj.crawler.service.impl.LinksElementFetcher
import org.jsoup.Jsoup
import spock.lang.Specification

import java.nio.charset.Charset

class LinksElementFetcherTest extends Specification {

  def 'Should return links from document'() {
    given:
    def linksElementsFetcher = new LinksElementFetcher()
    when:
    def elements = linksElementsFetcher.fetchElements(Jsoup.parse(
      Resources.toString(
        Resources.getResource('pages/page.html'), Charset.defaultCharset()
      )
    ))
    then:
    1 == elements.size()
  }
}
