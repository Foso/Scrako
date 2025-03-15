plugins {
    kotlin("jvm") version "2.1.0"
    kotlin("plugin.serialization") version "2.1.0"
}

version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
    // implementation(project(":scratch3"))
    // implementation(project(":scrako-core"))
    // implementation(project(":turbowarp"))
    implementation("de.jensklingenberg.scrako:turbowarp:0.8.7")
    implementation("de.jensklingenberg.scrako:scrako-core:0.8.7")
    implementation("de.jensklingenberg.scrako:scratch3:0.8.7")
    //kotlinpoet
    implementation("com.squareup:kotlinpoet:1.18.1")

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}