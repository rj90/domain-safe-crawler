package com.rafalj.crawler.service

import com.rafalj.crawler.service.impl.ElementFetcher
import com.rafalj.crawler.service.impl.WebCrawlerServiceImpl
import org.assertj.core.util.Sets
import org.jsoup.nodes.Document
import spock.lang.Specification

class WebCrawlerServiceTest extends Specification {

  private DocumentFetchingService documentFetchingService = Mock(DocumentFetchingService)
  private ElementFetcher importsElementFetcher = Mock(ElementFetcher)
  private ElementFetcher linksElementFetcher = Mock(ElementFetcher)
  private ElementFetcher mediaElementFetcher = Mock(ElementFetcher)

  def "Should crawl example site"() {
    given:
    def service = new WebCrawlerServiceImpl(
        documentFetchingService,
        importsElementFetcher,
        linksElementFetcher,
        mediaElementFetcher
    )
    when:
    def pageInfo = service.crawl("http://someUrl.com")
    then:
    1 * documentFetchingService.fetchDocument(_) >> new Document("http://someUrl.com")
    1 * importsElementFetcher.fetchElements(_) >> Sets.newLinkedHashSet("http://someUrl.com")
    1 * linksElementFetcher.fetchElements(_) >> Sets.newLinkedHashSet("http://someUrl.com", "http://otherUrl.com")
    1 * mediaElementFetcher.fetchElements(_) >> Sets.newLinkedHashSet("http://someUrl.com")
    null != pageInfo
  }

  def "Should return null on exception"() {
    given:
    def service = new WebCrawlerServiceImpl(
        documentFetchingService,
        importsElementFetcher,
        linksElementFetcher,
        mediaElementFetcher
    )
    when:
    def pageInfo = service.crawl("http://someUrl.com")
    then:
    1 * documentFetchingService.fetchDocument(_) >> {throw new IOException()}
    null == pageInfo
  }
}
