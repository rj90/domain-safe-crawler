package com.rafalj.crawler.service

import com.google.common.io.Resources
import com.rafalj.crawler.service.impl.ImportsElementFetcher
import org.jsoup.Jsoup
import spock.lang.Specification

import java.nio.charset.Charset

class ImportsElementFetcherTest extends Specification {

  def 'Should return imports from document'() {
    given:
    def importElementsFetcher = new ImportsElementFetcher()
    when:
    def elements = importElementsFetcher.fetchElements(Jsoup.parse(
      Resources.toString(
        Resources.getResource('pages/page.html'), Charset.defaultCharset()
      )
    ))
    then:
    1 == elements.size()
  }
}
