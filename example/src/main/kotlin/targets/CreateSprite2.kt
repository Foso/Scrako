package me.jens.targets

import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.ProjectBuilder
import de.jensklingenberg.scrako.common.addSprite
import de.jensklingenberg.scrako.common.getVariable
import de.jensklingenberg.scrako.common.scriptBuilder
import de.jensklingenberg.scrako.common.targetBuilder
import de.jensklingenberg.scratch.data.setVariable
import de.jensklingenberg.scratch.event.whenFlagClicked
import de.jensklingenberg.scratch.looks.say
import me.jens.spriteArrow

fun ProjectBuilder.createSprite2() {
    targetBuilder {
        addSprite(spriteArrow)
        scriptBuilder {
            val element = getVariable("myVar")
            whenFlagClicked()
            setVariable(element, IntBlock(1))
            say(element)
            // setVariable(element, IntBlock(1))
            //say(showPickerAs("text"))
        }
    }

}