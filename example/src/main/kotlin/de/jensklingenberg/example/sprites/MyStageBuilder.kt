package de.jensklingenberg.example.sprites

import BackdropOptions.PREVIOUS
import de.jensklingenberg.scrako.builder.Config
import de.jensklingenberg.scrako.builder.ProjectBuilder
import de.jensklingenberg.scrako.builder.scriptBuilder
import de.jensklingenberg.scrako.builder.stageBuilder
import de.jensklingenberg.scrako.common.backdrop
import de.jensklingenberg.scratch3.event.whenFlagClicked
import de.jensklingenberg.scratch3.looks.nextBackdrop
import switchBackdropTo

fun ProjectBuilder.MyStageBuilder() {
    stageBuilder {
        config = Config(costumes = setOf(backdrop))

        scriptBuilder {
            whenFlagClicked()
            nextBackdrop()
            switchBackdropTo(PREVIOUS)
        }
    }
}