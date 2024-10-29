package me.jens.targets

import de.jensklingenberg.scrako.builder.ProjectBuilder
import de.jensklingenberg.scrako.builder.addSprite
import de.jensklingenberg.scrako.common.getOrCreateList
import de.jensklingenberg.scrako.common.getOrCreateVariable
import de.jensklingenberg.scrako.common.scriptBuilder
import de.jensklingenberg.scrako.common.targetBuilder
import de.jensklingenberg.scratch.data.changeVariable
import de.jensklingenberg.scratch.event.whenFlagClicked
import de.jensklingenberg.scratch.looks.say
import me.jens.spriteArrow

fun ProjectBuilder.createSprite2() {

    targetBuilder("Sprite1") {
        addSprite(spriteArrow)
        scriptBuilder {
            //getOrCreateList("myList")
            val element = getOrCreateVariable("myVar")
            whenFlagClicked()
            changeVariable(element, 1.0)
            say(element)
            // setVariable(element, IntBlock(1))
            //say(showPickerAs("text"))
        }
    }

}