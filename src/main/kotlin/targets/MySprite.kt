package me.jens.targets

import de.jensklingenberg.scratch.Broadcast
import de.jensklingenberg.scratch.ScratchList
import de.jensklingenberg.scratch.addSprite
import de.jensklingenberg.scratch.blockBuilder
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.common.getVariable
import de.jensklingenberg.scratch.control.case
import de.jensklingenberg.scratch.control.switch
import de.jensklingenberg.scratch.createList
import de.jensklingenberg.scratch.event.whenFlagClicked
import de.jensklingenberg.scratch.looks.Effect.GHOST
import de.jensklingenberg.scratch.looks.StringBlock
import de.jensklingenberg.scratch.looks.changeEffectBy
import de.jensklingenberg.scratch.looks.say
import de.jensklingenberg.scratch.looks.setEffectTo
import de.jensklingenberg.scratch.model.Target
import de.jensklingenberg.scratch.model.createTarget
import de.jensklingenberg.scratch.motion.switchCostume
import de.jensklingenberg.scratch.operator.add
import de.jensklingenberg.scratch.procedures.ArgumentBoolean
import de.jensklingenberg.scratch.procedures.ArgumentString
import de.jensklingenberg.scratch.procedures.Input
import de.jensklingenberg.scratch.procedures.definition
import de.jensklingenberg.scratch.sensing.Answer
import de.jensklingenberg.scratch.sensing.SensingOptions.x_position
import de.jensklingenberg.scratch.sensing.of
import de.jensklingenberg.scratch.targetBuilder
import me.jens.spriteArrow


fun MyTarget(jensList: ScratchList): Target {
    val elements = Input(ArgumentBoolean("bool"))
    val elements1 = Input(ArgumentString("bool2"))


    val broadcast = Broadcast("hello")

    val target = targetBuilder {
        addSprite(spriteArrow)

        blockBuilder {
            val tt = getVariable("myVariable2")
            val users = createList("Users", listOf("Jens", "Martin", "Thomas"))

            whenFlagClicked()
            switchCostume(tt)
            say(tt)
            switchCostume("costume1")
            say(x_position.of(spriteArrow))
            changeEffectBy(GHOST, StringBlock("10"))
            setEffectTo(GHOST.name, add(3, 3))
        }

       blockBuilder {
            definition("Hey", true, listOf(elements))
            say(elements1.argument)
            switch(Answer) {
                case("Hello") {
                    say("Hello")
                }
                case("World") {
                    say("World")
                }
            }
        }
    }

    return target.build()
}

private operator fun String.unaryPlus(): ReporterBlock {
    return StringBlock(this)
}

