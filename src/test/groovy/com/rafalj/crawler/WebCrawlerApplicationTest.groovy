package com.rafalj.crawler

import com.rafalj.crawler.controller.WebCrawlerController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class WebCrawlerApplicationTest extends Specification {

  @Autowired (required = false)
  private WebCrawlerController webCrawlerController

  def 'When context is loaded then all expected beans are created'() {
    expect: 'the WebCrawlerController is created'
    webCrawlerController
  }
}
