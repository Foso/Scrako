plugins {
    id("java")
    kotlin("jvm")
    kotlin("plugin.serialization") version "2.0.21"
    id("com.vanniktech.maven.publish")
}

group = "de.jensklingenberg"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(project(":scrako-core"))
    implementation(libs.kotlin.serialization)

}

tasks.test {
    useJUnitPlatform()
}