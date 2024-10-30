package me.jens.targets

import de.jensklingenberg.scrako.builder.ProjectBuilder
import de.jensklingenberg.scrako.builder.addSprite
import de.jensklingenberg.scrako.builder.getOrCreateVariable
import de.jensklingenberg.scrako.common.scriptBuilder
import de.jensklingenberg.scrako.common.targetBuilder
import de.jensklingenberg.scratch.data.changeVariableBy
import de.jensklingenberg.scratch.event.whenFlagClicked
import de.jensklingenberg.scratch.looks.say
import me.jens.backdropSprite

fun ProjectBuilder.createSprite2() {

    targetBuilder("Sprite1") {
        addSprite(backdropSprite)
        scriptBuilder {
            //getOrCreateList("myList")
            val element = getOrCreateVariable("myVar")
            whenFlagClicked()
            changeVariableBy(element, 1.0)
            say(element)
            // setVariable(element, IntBlock(1))
            //say(showPickerAs("text"))
        }
    }

}