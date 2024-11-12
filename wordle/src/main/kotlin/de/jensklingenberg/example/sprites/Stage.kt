package de.jensklingenberg.de.jensklingenberg.example.sprites

import de.jensklingenberg.scrako.builder.Config
import de.jensklingenberg.scrako.builder.ProjectBuilder
import de.jensklingenberg.scrako.builder.createList
import de.jensklingenberg.scrako.builder.scriptBuilder
import de.jensklingenberg.scrako.builder.stageBuilder
import de.jensklingenberg.scrako.common.Argument
import de.jensklingenberg.scrako.common.ArgumentType
import de.jensklingenberg.scrako.common.Broadcast
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scrako.common.ScratchVariable
import de.jensklingenberg.scrako.common.backdrop
import de.jensklingenberg.scratch3.control.repeat
import de.jensklingenberg.scratch3.data.addToList
import de.jensklingenberg.scratch3.data.itemOfXList
import de.jensklingenberg.scratch3.data.setVariable
import de.jensklingenberg.scratch3.event.sendBroadcast
import de.jensklingenberg.scratch3.event.whenFlagClicked
import de.jensklingenberg.scratch3.extension.pen.eraseAll
import de.jensklingenberg.scratch3.procedures.call
import de.jensklingenberg.scratch3.procedures.define
import de.jensklingenberg.scratch3.sensing.Answer
import de.jensklingenberg.scratch3.sensing.ask
import debugger.log

fun ProjectBuilder.Stage(
    searchWord: ScratchVariable,
    broadcast: Broadcast,
    insertWords: ScratchList,
    sayAnswerBroadcast: Broadcast
) {
    stageBuilder {
        config = Config(costumes = listOf(backdrop))

        /**
         * Start
         */

        val germanWords = listOf(
            "Apfel", "BANANE", "Birne", "Blume", "Brot",
            "Buch", "Eiche", "Ente", "Fuchs", "Haus",
            "Hund", "Kater", "Katze", "Maus", "Pferd",
            "Rose", "Sonne", "Stuhl", "Tisch", "Vogel"
        )
        val wordList =
            createList("WORDLIST", germanWords)
        scriptBuilder {
            whenFlagClicked()
            eraseAll()

            setVariable(searchWord, itemOfXList(IntBlock(2), wordList))
            log(searchWord)
            repeat(5) {
                ask("What is the word")
                call("uppercase", listOf(Answer))
                sendBroadcast(broadcast)

            }
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