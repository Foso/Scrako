package de.jensklingenberg.de.jensklingenberg.example.sprites

import de.jensklingenberg.scrako.builder.ProjectBuilder
import de.jensklingenberg.scrako.builder.addBackdrop
import de.jensklingenberg.scrako.builder.scriptBuilder
import de.jensklingenberg.scrako.builder.stageBuilder
import de.jensklingenberg.scrako.common.Broadcast
import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scrako.common.ScratchVariable
import de.jensklingenberg.scrako.common.backdrop
import de.jensklingenberg.scratch3.control.repeat
import de.jensklingenberg.scratch3.data.addToList
import de.jensklingenberg.scratch3.data.setVariable
import de.jensklingenberg.scratch3.event.sendBroadcast
import de.jensklingenberg.scratch3.event.whenFlagClicked
import de.jensklingenberg.scratch3.extension.pen.eraseAll
import de.jensklingenberg.scratch3.looks.hide
import de.jensklingenberg.scratch3.motion.move
import de.jensklingenberg.scratch3.sensing.Answer
import de.jensklingenberg.scratch3.sensing.ask

fun ProjectBuilder.Stage(
    searchWord: ScratchVariable,
    broadcast: Broadcast,
    insertWords: ScratchList
) {
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
}