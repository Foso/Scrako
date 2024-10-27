plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "LLVMPoet"
include("example")
include("scratchgen")
include("scrako-core")
include("turbowarp")
