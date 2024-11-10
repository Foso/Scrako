package de.jensklingenberg

import de.jensklingenberg.example.sprites.MySprite1
import de.jensklingenberg.scrako.builder.addBackdrop
import de.jensklingenberg.scrako.builder.createBroadcast
import de.jensklingenberg.scrako.builder.createGlobalList
import de.jensklingenberg.scrako.builder.createGlobalVariable
import de.jensklingenberg.scrako.builder.projectBuilder
import de.jensklingenberg.scrako.builder.scriptBuilder
import de.jensklingenberg.scrako.builder.stageBuilder
import de.jensklingenberg.scrako.common.backdrop
import de.jensklingenberg.scratch3.control.repeat
import de.jensklingenberg.scratch3.data.addToList
import de.jensklingenberg.scratch3.data.setVariable
import de.jensklingenberg.scratch3.event.sendBroadcast
import de.jensklingenberg.scratch3.event.whenFlagClicked
import de.jensklingenberg.scratch3.extension.pen.eraseAll
import de.jensklingenberg.scratch3.looks.hide
import de.jensklingenberg.scratch3.sensing.Answer
import de.jensklingenberg.scratch3.sensing.ask
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
        val searchWord = createGlobalVariable("searchWord")
        val broadcast = createBroadcast("paint")
        val insertWords =
            createGlobalList("INSERTWORDS")
        stageBuilder {
            addBackdrop(listOf(backdrop))
            /**
             * Start
             */
            scriptBuilder {
                whenFlagClicked()
                hide()
                eraseAll()
                setVariable(searchWord, "HOUND")
                repeat(5) {
                    ask("What is the word")
                    sendBroadcast(broadcast)
                    addToList(insertWords, Answer)
                }
            }
        }
        MySprite1(broadcast, searchWord, insertWords)

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

