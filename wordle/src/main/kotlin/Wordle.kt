package de.jensklingenberg

import de.jensklingenberg.de.jensklingenberg.example.sprites.Stage
import de.jensklingenberg.example.sprites.MySprite1
import de.jensklingenberg.scrako.builder.createBroadcast
import de.jensklingenberg.scrako.builder.createGlobalList
import de.jensklingenberg.scrako.builder.createGlobalVariable
import de.jensklingenberg.scrako.builder.projectBuilder
import java.io.File


fun main() {
//xml:space="preserve" fill="#000000"

    val files =File("/Users/jens.klingenberg/Code/2024/LLVMPoet/wordle/src/main/resources/sprites").listFiles()

    files.forEach {
        val content = it.readText()
        val newContent = content.replace("xml:space=\"preserve\" fill=\"#000000\"","xml:space=\"preserve\" fill=\"#00FF00\"")
        File(it.parent + "/"+it.nameWithoutExtension+"Green."+it.extension).writeText(newContent)
    }

    files.forEach {
        val content = it.readText()
        val newContent = content.replace("xml:space=\"preserve\" fill=\"#000000\"","xml:space=\"preserve\" fill=\"#0000FF\"")
        File(it.parent + "/"+it.nameWithoutExtension+"Blue."+it.extension).writeText(newContent)
    }

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
