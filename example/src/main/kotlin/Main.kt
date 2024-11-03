package de.jensklingenberg

import de.jensklingenberg.example.sprites.Sprite2
import de.jensklingenberg.scrako.builder.createBroadcast
import de.jensklingenberg.scrako.builder.getGlobalVariable
import de.jensklingenberg.scrako.builder.projectBuilder
import de.jensklingenberg.scrako.builder.writeProject
import kotlinx.serialization.json.Json
import de.jensklingenberg.example.sprites.MySprite1
import de.jensklingenberg.example.imports.importer
import de.jensklingenberg.example.sprites.MyStageBuilder
import java.io.File

val json = Json {
    ignoreUnknownKeys = true
    coerceInputValues = true
    explicitNulls = false
}


fun main() {

    importer()


    val proj = projectBuilder {
        val myGlobalVar = getGlobalVariable("myGlobalVar", true)
        val paint = createBroadcast("paint")
        //MySprite1(paint)
        Sprite2(paint)
        //MyStageBuilder()
    }


    val outputPath = "/Users/jens.klingenberg/Code/2024/LLVMPoet/temp"
    val inputPath = "/Users/jens.klingenberg/Code/2024/LLVMPoet/example/src/main/resources/"

    val fileName = "test4.sb3"
    writeProject(
        proj.build(),
        inputPath,
        outputPath,
        fileName
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


fun readList(name: String): List<String> {
    return File(name).readLines()
}

