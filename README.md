# Domain Safe Crawler [![Build Status](https://travis-ci.org/rj90/domain-safe-crawler.svg?branch=master)](https://travis-ci.org/rj90/domain-safe-crawler) [![Coverage Status](https://coveralls.io/repos/github/rj90/domain-safe-crawler/badge.svg?branch=master&service=github)](https://coveralls.io/github/rj90/domain-safe-crawler?branch=master&service=github) [![MIT license](http://img.shields.io/badge/license-MIT-brightgreen.svg)](http://opensource.org/licenses/MIT)

Domain safe crawler is a tool which allow to search all links in one domain.

## Prerequisites
* Java 8 - On standalone build
* Docker and docker-compose - For docker configuration

## Building

To build crawler use following command
```bash
./gradlew build
```
If you want to make full rebuild you should clean whole project using:
```bash
./gradlew clean
```

## Installation

Crawler can be run as standalone server application on local machine or docker

### Standalone

Use following command to run it locally
```bash
./gradlew bootRun
```
### Docker

Use following command to run it on docker
```bash
./gradlew dockerBuildImage
./gradlew composeUp
```

## Testing

All tests are run during build. Also they can be run using this command
```bash
./gradlew test
```

## Trade offs

The main problem is caching - multithreading compromise.
There is no the best configuration option for all sites.
Sites with many repeatable links have shown algorithm is faster when cache is synchronized and crawling runs in single thread.
Sites with many different links (like forums), have shown that multithreading is more effective than caching. It was decided to use not synchronized cache and multithreading search

## TODO

* Display elements as dynamic graph
* Limit crawling depth
* Allow to search on external sites (as option)
* Allow to stop crawler and return already found links
* Add dynamic configuration for synchronized cache and multithreading search

## License

[MIT](https://choosealicense.com/licenses/mit/)