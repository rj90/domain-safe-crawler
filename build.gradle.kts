plugins {
	id("org.springframework.boot") version "2.1.4.RELEASE"
	id("com.bmuschko.docker-spring-boot-application") version "4.7.1"
	id("com.avast.gradle.docker-compose") version "0.9.2"
	java
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
	
	testImplementation("org.spockframework:spock-core:1.3-groovy-2.5")
	testImplementation("org.spockframework:spock-spring:1.3-groovy-2.5")
	testImplementation("org.codehaus.groovy:groovy-all:2.4.11',")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

docker {
	springBootApplication {
		tag.set("web-crawler:0.0.1")
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
