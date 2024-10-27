plugins {
    id("java")
    kotlin("jvm")
    kotlin("plugin.serialization") version "2.0.21"
}

group = "me.jens"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(project(":scratchgen"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")

}

tasks.test {
    useJUnitPlatform()
}