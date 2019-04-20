package com.rafalj.crawler.controller

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.web.servlet.MockMvc

import com.rafalj.crawler.model.PageInfo
import com.rafalj.crawler.service.WebCrawlerService

import spock.lang.Specification
import spock.mock.DetachedMockFactory

@WebMvcTest(controllers = [WebCrawlerController])
class WebCrawlerControllerTest extends Specification {

@Autowired
private MockMvc mvc

@Autowired
private WebCrawlerService webCrawlerService

  def 'Should return simple page info'() {
    when:
    def results = mvc.perform(
      get('/search')
        .param('url', 'http://someUrl.com')
    )
    then:
    1 * webCrawlerService.crawl(_) >> PageInfo.builder().url('someUrl').title('someTitle').build()
    results.andExpect(status().isOk())
      .andExpect(jsonPath('$.title').value('someTitle'))
      .andExpect(jsonPath('$.url').value('someUrl'))
  }

  @TestConfiguration
  static class Mocks {

    def factory = new DetachedMockFactory()

    @Bean
    WebCrawlerService webCrawlerService() {
      factory.Mock(WebCrawlerService)
    }
  }
}
