val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val koin_version: String by project
val openapi_version: String by project

plugins {
    application
    kotlin("jvm") version "1.5.30"
}

group = "com.application"
version = "0.0.1"
application {
    mainClass.set("com.application.ApplicationKt")
}

repositories {
    mavenCentral()
}

//tasks.withType<Test> {
//    useJUnitPlatform()
//}

tasks.test {
    testLogging.showStandardStreams = true
    useJUnitPlatform()
}


dependencies {
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.insert-koin:koin-core:$koin_version")
    implementation("io.javalin:javalin:3.13.3")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.8")
    implementation("io.prometheus:simpleclient_httpserver:0.11.0")
    implementation("com.zaxxer:HikariCP:3.1.0")
    implementation("org.jetbrains.exposed:exposed:0.17.13")
    implementation("org.postgresql:postgresql:42.2.5")
    implementation("org.flywaydb:flyway-core:6.5.2")
    implementation("com.natpryce:konfig:1.6.10.0")

    // Testing
    //testImplementation("io.insert-koin:koin-test:$koin_version")
    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
    //testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlin_version")
    testImplementation("io.mockk:mockk:1.9.3")
    testImplementation("org.junit.jupiter:junit-jupiter:5.4.2")
}