package me.jens.targets

import de.jensklingenberg.scrako.builder.ProjectBuilder
import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.builder.addCostume
import de.jensklingenberg.scrako.builder.addSprite
import de.jensklingenberg.scrako.common.Argument2
import de.jensklingenberg.scrako.common.ArgumentType
import de.jensklingenberg.scrako.common.BooleanBlock
import de.jensklingenberg.scrako.common.StringBlock
import de.jensklingenberg.scrako.common.scriptBuilder
import de.jensklingenberg.scrako.common.targetBuilder
import de.jensklingenberg.scratch.Broadcast
import de.jensklingenberg.scratch.event.whenFlagClicked
import de.jensklingenberg.scratch.looks.say
import de.jensklingenberg.scratch.operator.gt
import de.jensklingenberg.scratch.procedures.call
import de.jensklingenberg.scratch.procedures.define
import de.jensklingenberg.scratch.procedures.getArgs
import me.jens.costume1
import me.jens.spriteArrow


fun ProjectBuilder.MyTarget() {


    val broadcast = Broadcast("hello")

    targetBuilder("Sprite1") {
        addSprite(spriteArrow)
        addCostume(costume1)


        scriptBuilder {
            define(
                "Hallo",
                arguments = listOf(
                    Argument2("str", ArgumentType.BOOLEAN),
                    Argument2("str2", ArgumentType.NUMBER_OR_TEXT)
                )
            ) {
                val (str, str2) = getArgs()
                say(str)
                say(str2)
            }

            define("2222") {
                say("Hallo")
                say("Ciao")
            }
        }

        scriptBuilder {
            whenFlagClicked()
            hallo(gt(2, 3), StringBlock("Hallo"))
            //call("Hallo", listOf(Argument2("bool", BOOLEAN)))
        }

        scriptBuilder {
            whenFlagClicked()
        }

    }
}

private fun ScriptBuilder.hallo(stringBlock: BooleanBlock, stringBlock1: StringBlock) {
    call("Hallo", listOf(stringBlock, stringBlock1))
}
