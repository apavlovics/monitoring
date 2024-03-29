plugins {
    java
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.springframework.boot") version "2.5.6"
    id("io.freefair.lombok") version "6.2.0"
}

group = "lv.continuum"
version = "0.0.1"
description = "Monitoring"

repositories {
    mavenLocal()
    mavenCentral()
}

val springfoxVersion by extra("3.0.0")
val modelmapperVersion by extra("2.4.4")

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("io.springfox:springfox-boot-starter:$springfoxVersion")
    implementation("org.modelmapper:modelmapper:$modelmapperVersion")
    implementation("org.apache.commons:commons-lang3")
    runtimeOnly("org.hsqldb:hsqldb")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks {
    test {
        useJUnitPlatform()
    }
}
