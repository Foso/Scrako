package de.jensklingenberg

import de.jensklingenberg.scrako.builder.projectBuilder

fun main() {
    val outputPath = "/Users/jens.klingenberg/Code/2025/Scrako/temp"
    val inputPath = "/Users/jens.klingenberg/Code/2025/Scrako/example/src/main/resources/"
    val fileName = "wordle.sb3"

    val proj = projectBuilder {

    }

    proj.writeProject(
        inputPath,
        outputPath,
        fileName,
        true
    )

    killTurboWarp()
    startTurboWarp("${outputPath}/$fileName")
}

private fun startTurboWarp(filePath: String) {
    val processBuilder2 = ProcessBuilder("open", filePath)
    processBuilder2.inheritIO()
    val process2 = processBuilder2.start()
    process2.waitFor()
}

private fun killTurboWarp() {
    val processBuilder = ProcessBuilder("pkill", "-9", "TurboWarp")
    processBuilder.inheritIO()
    val process = processBuilder.start()
    process.waitFor()
}