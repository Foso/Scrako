package de.jensklingenberg

import de.jensklingenberg.example.sprites.MySprite1
import de.jensklingenberg.scrako.builder.createBroadcast
import de.jensklingenberg.scrako.builder.createGlobalVariable
import de.jensklingenberg.scrako.builder.projectBuilder
import kotlinx.serialization.json.Json
import java.io.File

val json = Json {
    ignoreUnknownKeys = true
    coerceInputValues = true
    explicitNulls = false
}


fun main() {

    val outputPath = "/Users/jens.klingenberg/Code/2024/LLVMPoet/temp"
    val inputPath = "/Users/jens.klingenberg/Code/2024/LLVMPoet/wordle/src/main/resources/"

    val fileName = "test4.sb3"

    val proj = projectBuilder {
        val myGlobalVar = createGlobalVariable("myGlobalVar", true)
        val paint = createBroadcast("paint")
        MySprite1(paint)
        //MyStageBuilder()
        writeProject(
            inputPath,
            outputPath,
            fileName
        )
    }

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


fun readList(name: String): List<String> {
    return File(name).readLines()
}

