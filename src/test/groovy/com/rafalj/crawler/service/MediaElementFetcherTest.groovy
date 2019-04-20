package com.rafalj.crawler.service

import com.google.common.io.Resources
import com.rafalj.crawler.service.impl.MediaElementFetcher
import org.jsoup.Jsoup
import spock.lang.Specification

import java.nio.charset.Charset

class MediaElementFetcherTest extends Specification {

  def 'Should return media from document'() {
    given:
    def mediaElementsFetcher = new MediaElementFetcher()
    when:
    def elements = mediaElementsFetcher.fetchElements(Jsoup.parse(
      Resources.toString(
        Resources.getResource('pages/page.html'), Charset.defaultCharset()
      )
    ))
    then:
    1 == elements.size()
  }
}
