plugins {
    kotlin("jvm")
    kotlin("plugin.serialization") version "2.1.0"
    id("com.vanniktech.maven.publish")
}
val enableSigning = project.hasProperty("signingInMemoryKey")

group = "de.jensklingenberg"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    api(project(":scrako-core"))
    implementation(libs.kotlin.serialization)
    testImplementation(libs.junit)
    implementation(libs.mockk)
    implementation(libs.mockito.kotlin)
}


kotlin {
    explicitApiWarning()
    jvmToolchain(21)
}


mavenPublishing {
    coordinates(
        libs.versions.groupId.get(),
        "scratch3",
        libs.versions.coreVersion.get(),
    )
    publishToMavenCentral()
    //  publishToMavenCentral(SonatypeHost.S01) // for publishing through s01.oss.sonatype.org
    if (enableSigning) {
        signAllPublications()
    }
}

publishing {
    publications {
        create<MavenPublication>("default") {
            //artifact(tasks["sourcesJar"])
            // artifact(tasks["javadocJar"])

            pom {
                name.set(project.name)
                description.set("Scratch3 Blocks for Scrako")
                url.set("https://github.com/Foso/Scrako")

                licenses {
                    license {
                        name.set("Apache License 2.0")
                        url.set("https://github.com/Foso/Scrako/blob/main/LICENSE.txt")
                    }
                }
                scm {
                    url.set("https://github.com/Foso/Scrako")
                    connection.set("scm:git:git://github.com/Foso/Scrako.git")
                }
                developers {
                    developer {
                        name.set("Jens Klingenberg")
                        url.set("https://github.com/Foso")
                    }
                }
            }
        }
    }

    repositories {
        if (
            hasProperty("sonatypeUsername") &&
            hasProperty("sonatypePassword") &&
            hasProperty("sonatypeSnapshotUrl") &&
            hasProperty("sonatypeReleaseUrl")
        ) {
            maven {
                val url =
                    when {
                        "SNAPSHOT" in version.toString() -> property("sonatypeSnapshotUrl")
                        else -> property("sonatypeReleaseUrl")
                    } as String
                setUrl(url)
                credentials {
                    username = property("sonatypeUsername") as String
                    password = property("sonatypePassword") as String
                }
            }
        }
    }
}