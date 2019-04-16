# Domain Safe Crawler

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