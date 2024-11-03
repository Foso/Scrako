package de.jensklingenberg.example.sprites

import BackdropOptions.PREVIOUS
import de.jensklingenberg.scrako.builder.ProjectBuilder
import de.jensklingenberg.scrako.builder.addBackdrop
import de.jensklingenberg.scrako.builder.addCostumes
import de.jensklingenberg.scrako.builder.scriptBuilder
import de.jensklingenberg.scrako.builder.stageBuilder
import de.jensklingenberg.scrako.common.backdrop
import de.jensklingenberg.scratch3.event.whenFlagClicked
import de.jensklingenberg.scratch3.looks.nextBackdrop
import de.jensklingenberg.scratch3.looks.say
import de.jensklingenberg.scratch3.motion.move
import switchBackdropTo

fun ProjectBuilder.MyStageBuilder() {
    stageBuilder {
        addBackdrop(listOf(backdrop))

        scriptBuilder {
            whenFlagClicked()
            nextBackdrop()
            switchBackdropTo(PREVIOUS)
        }
    }
}