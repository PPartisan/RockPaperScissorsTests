plugins {
    kotlin("jvm") version "1.9.23"
    id("org.jetbrains.kotlinx.kover").version("0.8.3")
    id("io.gitlab.arturbosch.detekt").version("1.23.3")
}

group = "com.github.ppartisan.rps"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("io.kotest:kotest-assertions-core:5.9.1")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.1.0")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}