val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val koin_version: String by project
val openapi_version: String by project

plugins {
    application
    kotlin("jvm") version "1.5.30"
}

group = "com.example"
version = "0.0.1"
application {
    mainClass.set("com.example.ApplicationKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.insert-koin:koin-core:$koin_version")
    implementation("org.openapitools:openapi-generator-gradle-plugin:$openapi_version")
    implementation("io.springfox:springfox-swagger2:2.7.0")

    // Testing
    testImplementation("io.insert-koin:koin-test:$koin_version")
    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlin_version")
}

//generateSwagger {
//    platform.set("kotlin")
//    packageName.set("com.yelp.codegen.samples")
//    specName.set("sample_specs")
//    specVersion.set("1.0.0")
//    inputFile.set(file("../sample_specs.json"))
//    outputDir.set(project.layout.projectDirectory.dir("./src/main/java/"))
//    features {
//        headersToRemove.add("Accept-Language")
//    }
//}