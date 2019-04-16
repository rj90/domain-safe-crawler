package com.rafalj.crawler

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class WebCrawlerApplicationSpec extends Specification {

    def "context load"() {
        when:
        def x = 1
        then:
        1 == 1
    }
}
