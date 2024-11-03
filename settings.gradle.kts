plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}


rootProject.name = "Scrako"
include("example")
include("scratch3")
include("scrako-core")
include("turbowarp")

include("import")
