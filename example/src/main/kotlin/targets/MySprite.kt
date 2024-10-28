package me.jens.targets

import de.jensklingenberg.scrako.builder.ProjectBuilder
import de.jensklingenberg.scrako.builder.addSprite
import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scrako.common.getOrCreateVariable
import de.jensklingenberg.scrako.common.scriptBuilder
import de.jensklingenberg.scrako.common.targetBuilder
import de.jensklingenberg.scratch.Broadcast
import de.jensklingenberg.scratch.control.forever
import de.jensklingenberg.scratch.data.changeVariable
import de.jensklingenberg.scratch.event.whenFlagClicked
import de.jensklingenberg.scratch.looks.say
import de.jensklingenberg.scratch.procedures.ArgumentBoolean
import de.jensklingenberg.scratch.procedures.ArgumentString
import de.jensklingenberg.scratch.procedures.Input
import de.jensklingenberg.scratch.procedures.define
import me.jens.sprite1


fun ProjectBuilder.MyTarget(jensList: ScratchList) {
    val elements = Input(ArgumentBoolean("bool"))
    val elements1 = Input(ArgumentString("bool2"))

    val broadcast = Broadcast("hello")

    targetBuilder("Sprite1") {
        addSprite(sprite1)

        scriptBuilder {
            define("Hallo"){
                say("Hallo")
                say("Ciao")
            }

            define("2222"){
                say("Hallo")
                say("Ciao")
            }
        }

        scriptBuilder {
            val element = getOrCreateVariable("myVar")
            whenFlagClicked()
            changeVariable(element, 1.0)
            say(element)

            //say(showPickerAs("text"))
        }

    }
}
