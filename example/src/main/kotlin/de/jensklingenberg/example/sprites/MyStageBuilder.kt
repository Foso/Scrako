package me.jens.de.jensklingenberg.example.sprites

import de.jensklingenberg.scrako.builder.ProjectBuilder
import de.jensklingenberg.scrako.builder.addCostumes
import de.jensklingenberg.scrako.builder.scriptBuilder
import de.jensklingenberg.scrako.builder.stageBuilder
import de.jensklingenberg.scrako.common.backdrop
import de.jensklingenberg.scratch3.event.whenFlagClicked
import de.jensklingenberg.scratch3.looks.say

fun ProjectBuilder.MyStageBuilder() {
    stageBuilder {
        addCostumes(listOf(backdrop))

        scriptBuilder {
            whenFlagClicked()
            say("Hello!")
        }
    }
}