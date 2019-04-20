package com.rafalj.crawler.service

import com.rafalj.crawler.configuration.CrawlerProperties
import com.rafalj.crawler.service.impl.DocumentFetchingServiceImpl
import spock.lang.Specification

class DocumentFetchingServiceTest extends Specification {

  private CrawlerProperties crawlerProperties = new CrawlerProperties()

  def "Should fetch document"() {
    given:
    crawlerProperties.timeout = 10000
    def documentFetchService = new DocumentFetchingServiceImpl(crawlerProperties)
    when:
    def document = documentFetchService.fetchDocument("http://google.com")
    then:
    null != document
  }
}
