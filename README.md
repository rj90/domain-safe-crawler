# Domain Safe Crawler [![Build Status](https://travis-ci.org/rj90/domain-safe-crawler.svg?branch=master)](https://travis-ci.org/rj90/domain-safe-crawler) [![Coverage Status](https://coveralls.io/repos/github/rj90/domain-safe-crawler/badge.svg?branch=master)](https://coveralls.io/github/rj90/domain-safe-crawler?branch=master) [![MIT license](http://img.shields.io/badge/license-MIT-brightgreen.svg)](http://opensource.org/licenses/MIT)

Domain safe crawler is a web crawler which search all links in one domain

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

## TODO
* Parallel crawling
* Display elements as dynamic graph
* Limit crawling depth
* Allow to search on external sites (as option)

## License
[MIT](https://choosealicense.com/licenses/mit/)