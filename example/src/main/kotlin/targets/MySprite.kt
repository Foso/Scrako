package me.jens.targets

import de.jensklingenberg.scrako.builder.ProjectBuilder
import de.jensklingenberg.scrako.builder.addCostume
import de.jensklingenberg.scrako.builder.addSprite
import de.jensklingenberg.scrako.common.StringBlock
import de.jensklingenberg.scrako.common.getOrCreateList
import de.jensklingenberg.scrako.common.getOrCreateVariable
import de.jensklingenberg.scrako.common.scriptBuilder
import de.jensklingenberg.scrako.common.targetBuilder
import de.jensklingenberg.scratch.Broadcast
import de.jensklingenberg.scratch.data.changeVariable
import de.jensklingenberg.scratch.event.whenFlagClicked
import de.jensklingenberg.scratch.looks.say
import de.jensklingenberg.scratch.operator.add
import de.jensklingenberg.scratch.procedures.Argument2
import de.jensklingenberg.scratch.procedures.ArgumentType
import de.jensklingenberg.scratch.procedures.call
import de.jensklingenberg.scratch.procedures.define
import me.jens.costume1
import me.jens.spriteArrow


fun ProjectBuilder.MyTarget() {


    val broadcast = Broadcast("hello")

    targetBuilder("Sprite1") {
        addSprite(spriteArrow)
        addCostume(costume1)

        getOrCreateList("jens", listOf("Hallo", "Ciao"))
        val jens = getOrCreateVariable("jens")

        scriptBuilder {
            define(
                "Hallo",
                arguments = listOf(Argument2("mybool", ArgumentType.NUMBER_OR_TEXT))
            ) {
                say("Hallo")
                say("Ciao")
                changeVariable(jens, add(1.0, 2.0))
            }

            define("2222") {
                say("Hallo")
                say("Ciao")
            }
        }

        scriptBuilder {
            whenFlagClicked()
            call("Hallo", StringBlock("Hallo"))
            //call("Hallo", listOf(Argument2("bool", BOOLEAN)))
        }

        scriptBuilder {
            whenFlagClicked()
        }

    }
}
