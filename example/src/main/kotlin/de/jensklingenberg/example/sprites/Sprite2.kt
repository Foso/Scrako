package de.jensklingenberg.example.sprites

import de.jensklingenberg.example.costumes.Blockc
import de.jensklingenberg.example.costumes.blockA
import de.jensklingenberg.example.costumes.blockB
import de.jensklingenberg.scrako.builder.Config
import de.jensklingenberg.scrako.builder.ProjectBuilder
import de.jensklingenberg.scrako.builder.createVariable
import de.jensklingenberg.scrako.builder.scriptBuilder
import de.jensklingenberg.scrako.builder.spriteBuilder
import de.jensklingenberg.scrako.common.Broadcast
import de.jensklingenberg.scratch3.control.forever
import de.jensklingenberg.scratch3.control.ifThen
import de.jensklingenberg.scratch3.control.repeat
import de.jensklingenberg.scratch3.data.setVariable
import de.jensklingenberg.scratch3.event.Key
import de.jensklingenberg.scratch3.event.whenFlagClicked
import de.jensklingenberg.scratch3.event.whenKeyPress
import de.jensklingenberg.scratch3.looks.hide
import de.jensklingenberg.scratch3.looks.say
import de.jensklingenberg.scratch3.looks.show
import de.jensklingenberg.scratch3.operator.eq
import de.jensklingenberg.scratch3.operator.plus
import de.jensklingenberg.scratch3.sensing.Answer
import debugger.log

fun ProjectBuilder.Sprite2(paint: Broadcast) {
    spriteBuilder("Sprite2") {
        config= Config(costumes = setOf(blockA, blockB, Blockc))
        //addPosition(100.0, 150.0)

        val test = createVariable("test")
        val test2 = createVariable("test2")
        scriptBuilder {
            whenFlagClicked()
            hide()
            say(Answer)
            setVariable(test, 42)

            repeat(test plus test2) {
                say(test plus 42)
            }
            forever {
                say("bye")
            }
        }

        scriptBuilder {
            whenKeyPress(Key.A)
            show()
            ifThen(1 eq 1) {
                say("Hello")
                log("Hello")
            }

        }
    }
}


