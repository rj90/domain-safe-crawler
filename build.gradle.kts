plugins {
	id("org.springframework.boot") version "2.1.4.RELEASE"
	java
}

apply {
	plugin("io.spring.dependency-management")
}

group = "com.rafalj.crawler"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_12
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}
