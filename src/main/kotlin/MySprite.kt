package me.jens

import de.jensklingenberg.scratch.Broadcast
import de.jensklingenberg.scratch.ScratchList
import de.jensklingenberg.scratch.Sprite
import de.jensklingenberg.scratch.blockBuilder
import de.jensklingenberg.scratch.common.createBlocks23
import de.jensklingenberg.scratch.control.case
import de.jensklingenberg.scratch.control.switch
import de.jensklingenberg.scratch.control.waitUntil
import de.jensklingenberg.scratch.event.whenIRecieve
import de.jensklingenberg.scratch.event.whenStartAsClone
import de.jensklingenberg.scratch.looks.say
import de.jensklingenberg.scratch.model.Target
import de.jensklingenberg.scratch.model.createTarget
import de.jensklingenberg.scratch.motion.RotationStyle.ALL_AROUND
import de.jensklingenberg.scratch.motion.changeXby
import de.jensklingenberg.scratch.motion.glideToXY
import de.jensklingenberg.scratch.motion.setRotationStyle
import de.jensklingenberg.scratch.operator.random
import de.jensklingenberg.scratch.procedures.ArgumentBoolean
import de.jensklingenberg.scratch.procedures.ArgumentString
import de.jensklingenberg.scratch.procedures.Input
import de.jensklingenberg.scratch.procedures.definition
import de.jensklingenberg.scratch.sensing.Answer
import de.jensklingenberg.scratch.sensing.ColorIsTouchingColor
import de.jensklingenberg.scratch.sound.playSound


fun MySprite(jensList: ScratchList): Target {
    val elements = Input(ArgumentBoolean("bool"))
    val elements1 = Input(ArgumentString("bool2"))

    val sprite = Sprite(
        "Sprite1", listOf(
            costume1,
            costum2
        ), listOf(
            sound1,
            sound2
        )
    )
    val broadcast = Broadcast("hello")

    val list = blockBuilder {
        whenIRecieve(broadcast)
        say(random(1, 10))
        setRotationStyle(ALL_AROUND)
        glideToXY(1.0, 2.0, 3.0)
    }

    val list2 = whenFlagClicked(jensList, elements)

    val list3 = blockBuilder {

        definition("Hey", true, listOf(elements))
        say(elements1.argument)
    }

    val blockMap = createBlocks23(listOf(list2))

    return createTarget(blockMap, sprite, emptyList(), listOf(jensList))
}




private fun whenFlagClicked(
    jensList: ScratchList,
    elements: Input
) = blockBuilder {

  // say(lt(1, Not(lt(1, 2))))
    //waitUntil(TouchingColor("#1f9226"))
    whenStartAsClone()
    waitUntil(ColorIsTouchingColor("#1f9226", "#1f9226"))
    changeXby(24)
    playSound("Test")
    switch(Answer){
        case(Answer){
            say("1")
        }
        case("Neee"){
            say("1")
        }

        case("32"){
            say(Answer)
        }

        case("Martin"){
            say("Martin")
        }

        case("Thomas"){
            say("Thomas")
        }
    }
}
