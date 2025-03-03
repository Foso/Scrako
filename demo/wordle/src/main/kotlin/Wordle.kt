package de.jensklingenberg

import de.jensklingenberg.de.jensklingenberg.example.sprites.MySprite1
import de.jensklingenberg.de.jensklingenberg.example.sprites.Stage
import de.jensklingenberg.scrako.builder.Config
import de.jensklingenberg.scrako.builder.createBroadcast
import de.jensklingenberg.scrako.builder.createGlobalList
import de.jensklingenberg.scrako.builder.createGlobalVariable
import de.jensklingenberg.scrako.builder.projectBuilder
import de.jensklingenberg.scrako.builder.scriptBuilder
import de.jensklingenberg.scrako.builder.spriteBuilder
import de.jensklingenberg.scratch3.common.cat
import de.jensklingenberg.scratch3.event.whenFlagClicked
import de.jensklingenberg.scratch3.looks.say


fun main() {

    val outputPath = "/Users/jens.klingenberg/Code/2025/Scrako/temp"
    val inputPath = "/Users/jens.klingenberg/Code/2025/Scrako/demo/wordle/src/main/resources/"

    val fileName = "test4.sb3"

    val proj = projectBuilder {
        val searchWord = createGlobalVariable("searchWord")
        val broadcast = createBroadcast("paint")
        val sayAnswerBroadcast = createBroadcast("sayAnswer")
        val insertedWords =
            createGlobalList("INSERTWORDS")
        Stage(searchWord, broadcast, insertedWords, sayAnswerBroadcast)
        MySprite1(broadcast, searchWord, insertedWords, sayAnswerBroadcast)
        spriteBuilder("Sprite2") {
            scriptBuilder {
                config = Config(costumes = setOf(cat), visible = false)
                whenFlagClicked()
                say("Hello")
            }
        }
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
