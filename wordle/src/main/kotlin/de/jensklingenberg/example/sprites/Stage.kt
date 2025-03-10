package de.jensklingenberg.de.jensklingenberg.example.sprites

import de.jensklingenberg.scrako.builder.Config
import de.jensklingenberg.scrako.builder.ProjectBuilder
import de.jensklingenberg.scrako.builder.scriptBuilder
import de.jensklingenberg.scrako.builder.stageBuilder
import de.jensklingenberg.scrako.common.Argument
import de.jensklingenberg.scrako.common.ArgumentType
import de.jensklingenberg.scrako.common.Broadcast
import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scrako.common.ScratchVariable
import de.jensklingenberg.scrako.common.backdrop
import de.jensklingenberg.scratch3.data.addToList
import de.jensklingenberg.scratch3.data.setVariable
import de.jensklingenberg.scratch3.event.sendBroadcast
import de.jensklingenberg.scratch3.event.whenFlagClicked
import de.jensklingenberg.scratch3.procedures.define

fun ProjectBuilder.Stage(
    insertWords: ScratchList,
    sayAnswerBroadcast: Broadcast,
    searchWord: ScratchVariable
) {
    stageBuilder {
        config = Config(costumes = setOf(backdrop), visible = false)

        scriptBuilder {
            whenFlagClicked()
            setVariable(searchWord,"Bye")
            sendBroadcast(sayAnswerBroadcast)
        }

        scriptBuilder {
            define("uppercase", arguments = listOf(Argument("word", ArgumentType.NUMBER_OR_TEXT))) {
                val (word) = it.getArgs()
                addToList(insertWords, word)
            }
        }
    }
}