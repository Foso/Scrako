package me.jens

import de.jensklingenberg.scratch.Broadcast
import de.jensklingenberg.scratch.ScratchList
import de.jensklingenberg.scratch.blockBuilder
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.common.createBlocks23
import de.jensklingenberg.scratch.common.createVariable
import de.jensklingenberg.scratch.control.case
import de.jensklingenberg.scratch.control.switch
import de.jensklingenberg.scratch.control.waitUntil
import de.jensklingenberg.scratch.createList
import de.jensklingenberg.scratch.event.whenFlagClicked
import de.jensklingenberg.scratch.event.whenStartAsClone
import de.jensklingenberg.scratch.looks.Effect
import de.jensklingenberg.scratch.looks.StringBlock
import de.jensklingenberg.scratch.looks.changeEffectBy
import de.jensklingenberg.scratch.looks.say
import de.jensklingenberg.scratch.model.Target
import de.jensklingenberg.scratch.model.createTarget
import de.jensklingenberg.scratch.motion.changeXby
import de.jensklingenberg.scratch.procedures.ArgumentBoolean
import de.jensklingenberg.scratch.procedures.ArgumentString
import de.jensklingenberg.scratch.procedures.Input
import de.jensklingenberg.scratch.procedures.definition
import de.jensklingenberg.scratch.sensing.Answer
import de.jensklingenberg.scratch.sensing.SensingOptions.x_position
import de.jensklingenberg.scratch.sensing.colorIsTouchingColor
import de.jensklingenberg.scratch.sensing.sensingOf
import de.jensklingenberg.scratch.sound.playSound


fun MyTarget(jensList: ScratchList): Target {
    val elements = Input(ArgumentBoolean("bool"))
    val elements1 = Input(ArgumentString("bool2"))


    val broadcast = Broadcast("hello")

    val list = blockBuilder {
        val tt = createVariable("myVariable2")
        val users = createList("Users", listOf("Jens", "Martin", "Thomas"))
        //whenGreaterThan(GreaterThanOption.TIMER, 3.0)
        whenFlagClicked()
        say(sensingOf(x_position, spriteArrow))
        changeEffectBy(Effect.GHOST, StringBlock("10"))
    }

    val list2 = whenFlagClicked(jensList, elements)

    val list3 = blockBuilder {

        definition("Hey", true, listOf(elements))
        say(elements1.argument)
    }

    val blockMap = createBlocks23(listOf(list.childs))

    return createTarget(blockMap, sprite, emptyList(), setOf(jensList) + list.lists, list.variables)
}

private operator fun String.unaryPlus(): ReporterBlock {
    return StringBlock(this)
}


private fun whenFlagClicked(
    jensList: ScratchList,
    elements: Input
) = blockBuilder {

    // say(lt(1, Not(lt(1, 2))))
    //waitUntil(TouchingColor("#1f9226"))
    whenStartAsClone()
    waitUntil(colorIsTouchingColor("#1f9226", "#1f9226"))
    changeXby(24)
    playSound("Test")
    switch(Answer) {
        case(Answer) {
            say("1")
        }
        case("Neee") {
            say("1")
        }

        case("32") {
            say(Answer)
        }

        case("Martin") {
            say("Martin")
        }

        case("Thomas") {
            say("Thomas")
        }
    }
}
