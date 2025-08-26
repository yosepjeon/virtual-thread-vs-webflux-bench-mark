plugins {
    kotlin("jvm") version "2.1.21"
    kotlin("plugin.spring") version "2.1.21"
}

group = "com.yosep.virtual_thread_vs_webflux_bench_mark_main"
version = ""

repositories { mavenCentral() }

dependencies {
    implementation(platform("org.springframework.boot:spring-boot-dependencies:3.5.4"))

    implementation("org.springframework.boot:spring-boot-starter-webflux")

    // r2dbc
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    runtimeOnly("com.mysql:mysql-connector-j")
    runtimeOnly("io.asyncer:r2dbc-mysql")
    implementation("io.r2dbc:r2dbc-pool")

    // mongo
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // redis
    implementation("org.springframework.boot:spring-boot-starter-data-redis-reactive")
    implementation("org.redisson:redisson:3.50.0")

    // kafka
    implementation("org.springframework.kafka:spring-kafka")
    implementation("io.projectreactor.kafka:reactor-kafka")
    implementation("org.apache.kafka:kafka-clients")

    // docker
    implementation("org.springframework.boot:spring-boot-docker-compose")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("io.rest-assured:spring-web-test-client")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation(kotlin("test"))
}

// *** 이 모듈만 21로 강제 ***
java {
    toolchain { languageVersion.set(JavaLanguageVersion.of(21)) }
}

kotlin {
    jvmToolchain(21)
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21)
    }
}

// (선택) 코틀린 모듈 안의 Java 태스크도 21로 고정
tasks.withType<JavaCompile> {
    options.release.set(21)
}

tasks.test { useJUnitPlatform() }
