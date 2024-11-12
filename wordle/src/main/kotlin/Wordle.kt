package de.jensklingenberg

import de.jensklingenberg.de.jensklingenberg.example.sprites.Stage
import de.jensklingenberg.example.sprites.MySprite1
import de.jensklingenberg.scrako.builder.createBroadcast
import de.jensklingenberg.scrako.builder.createGlobalList
import de.jensklingenberg.scrako.builder.createGlobalVariable
import de.jensklingenberg.scrako.builder.projectBuilder


fun main() {

    val outputPath = "/Users/jens.klingenberg/Code/2024/LLVMPoet/temp"
    val inputPath = "/Users/jens.klingenberg/Code/2024/LLVMPoet/wordle/src/main/resources/"

    val fileName = "test4.sb3"

    val proj = projectBuilder {
        val searchWord = createGlobalVariable("searchWord")
        val broadcast = createBroadcast("paint")
        val sayAnswerBroadcast = createBroadcast("sayAnswer")
        val insertedWords =
            createGlobalList("INSERTWORDS")
        Stage(searchWord, broadcast, insertedWords,sayAnswerBroadcast)
        MySprite1(broadcast, searchWord, insertedWords,sayAnswerBroadcast)
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
