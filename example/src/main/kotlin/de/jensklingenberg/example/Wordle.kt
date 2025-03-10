package de.jensklingenberg.de.jensklingenberg.example

import de.jensklingenberg.de.jensklingenberg.example.sprites.Stage
import de.jensklingenberg.scrako.builder.Config
import de.jensklingenberg.scrako.builder.createBroadcast
import de.jensklingenberg.scrako.builder.createGlobalList
import de.jensklingenberg.scrako.builder.createGlobalVariable
import de.jensklingenberg.scrako.builder.projectBuilder
import de.jensklingenberg.scrako.builder.scriptBuilder
import de.jensklingenberg.scrako.builder.spriteBuilder
import de.jensklingenberg.scrako.common.Argument
import de.jensklingenberg.scrako.common.ArgumentType
import de.jensklingenberg.scrako.common.StringBlock
import de.jensklingenberg.scratch3.common.apple
import de.jensklingenberg.scratch3.common.cat
import de.jensklingenberg.scratch3.common.costume2
import de.jensklingenberg.scratch3.control.ifThen
import de.jensklingenberg.scratch3.control.wait
import de.jensklingenberg.scratch3.event.whenIReceiveBroadcast
import de.jensklingenberg.scratch3.looks.say
import de.jensklingenberg.scratch3.motion.move
import de.jensklingenberg.scratch3.operator.eq
import de.jensklingenberg.scratch3.procedures.call
import de.jensklingenberg.scratch3.procedures.define


fun main() {

    val outputPath = "/Users/jens.klingenberg/Code/2025/Scrako/temp"
    val inputPath = "/Users/jens.klingenberg/Code/2025/Scrako/demo/wordle/src/main/resources/"

    val fileName = "test4.sb3"

    val proj = projectBuilder {
        val searchWord = createGlobalVariable("searchWord")
        val jensList = createGlobalList("jensList", listOf("1", "2", "3"))
        val sayAnswerBroadcast = createBroadcast("sayAnswer")
        val insertedWords =
            createGlobalList("INSERTWORDS")
        Stage(insertedWords, sayAnswerBroadcast, searchWord)
        spriteBuilder("Sprite2") {
            scriptBuilder {
                config = Config(costumes = setOf(cat), visible = true)
                whenIReceiveBroadcast(sayAnswerBroadcast)
                say("Hello")
                wait(2)
                call(
                    "foo",
                    listOf(StringBlock("Test"), StringBlock("hello") eq StringBlock("world"))
                )
            }

            scriptBuilder {
                define(
                    name = "foo",
                    withoutRefresh = true,
                    arguments = listOf(
                        Argument("bar", ArgumentType.NUMBER_OR_TEXT),
                        Argument("baz", ArgumentType.BOOLEAN)
                    )
                )
                {
                    val (bar, baz) = it.getArgs()
                    say(bar)
                    ifThen(baz) {
                        move(30)
                    }
                }

            }
        }
        spriteBuilder("Sprite3") {
            scriptBuilder {
                config = Config(costumes = setOf(apple, costume2), visible = true, posX = 143.0, posY = -24.0)
                whenIReceiveBroadcast(sayAnswerBroadcast)
                say(searchWord)
            }
        }
    }

    proj.writeProject(
        inputPath,
        outputPath,
        fileName,
        false
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
