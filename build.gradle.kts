import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.5"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    kotlin("jvm") version "1.6.21"
    // automatically opens classes and methods annotated or meta-annotated with Spring annotations
    // useful to be able to create @Configuration or @Transactional beans without having to add the open qualifier required by CGLIB proxies
    kotlin("plugin.spring") version "1.6.21"
    // able to use Kotlin's non-nullable properties with JPA
    // generates no-arg constructors for any class annotated with @Entity, @MappedSuperclass or @Embeddable
    kotlin("plugin.jpa") version "1.6.21"
    // in order to make entities open (for lazy fetching to work)
    kotlin("plugin.allopen") version "1.6.21"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-mustache")
    implementation("org.springframework.boot:spring-boot-starter-web")
    // adds support for serialization/deserialization of Kotlin classes and data classes
    // (single constructor classes can be used automatically, and those with secondary constructors or static factories are also supported)
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    // kotlin reflection library
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    // java 8 variant of kotlin standard library
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    runtimeOnly("com.h2database:h2")
    // use Mockk which is similar to Mockito but better suited for Kotlin
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "junit")
        exclude(module = "mockito-core")
    }
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    // use SpringMockK provides similar @MockkBean and @SpykBean annotations for Mockk
    testImplementation("com.ninja-squad:springmockk:3.0.1")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        // enable nullability checking support for the whole spring framework API
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.Embeddable")
    annotation("javax.persistence.MappedSuperclass")
}