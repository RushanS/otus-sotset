import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "ru.otus.sotset"
version = "1.0.0"

repositories {
    mavenCentral()
}

plugins {
    val kotlinVersion = "1.9.10"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    id("org.springframework.boot") version "3.1.3"
    id("io.spring.dependency-management") version "1.1.3"
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("jakarta.validation:jakarta.validation-api")
    implementation("jakarta.annotation:jakarta.annotation-api:2.1.0")
    implementation("org.liquibase:liquibase-core")
    implementation("io.micrometer:micrometer-registry-prometheus")
    runtimeOnly("com.mysql:mysql-connector-j")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}
