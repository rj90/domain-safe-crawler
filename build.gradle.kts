plugins {
	id("com.gradle.build-scan") version "2.1"
	id("org.springframework.boot") version "2.1.4.RELEASE"
	id("com.bmuschko.docker-spring-boot-application") version "4.7.1"
	id("com.avast.gradle.docker-compose") version "0.9.2"
	id("io.freefair.lombok") version "3.2.0"
	id("com.github.kt3k.coveralls") version "2.8.2"
//	checkstyle
	java
	jacoco
	groovy
}

apply {
	plugin("io.spring.dependency-management")
	plugin("docker-compose")
}

group = "com.rafalj.crawler"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_1_8
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.jsoup:jsoup:1.7.2")

	testImplementation("org.spockframework:spock-core:1.3-groovy-2.5")
	testImplementation("org.spockframework:spock-spring:1.3-groovy-2.5")
	testImplementation("org.codehaus.groovy:groovy-all:2.5.6")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

buildScan {
	termsOfServiceUrl = "https://gradle.com/terms-of-service"
	termsOfServiceAgree = "yes"
}

docker {
	springBootApplication {
		tag.set("web-crawler:0.0.1")
	}
}

tasks.withType<JacocoReport>().configureEach {
	reports {
		html.isEnabled = true
		xml.isEnabled = true
		csv.isEnabled = false
	}
}

tasks {
	val dockerBuildImage by existing

	"composeBuild" {
		dependsOn(dockerBuildImage)
	}

	"composeUp" {
		dependsOn(dockerBuildImage)
	}
}
