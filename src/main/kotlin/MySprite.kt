package me.jens

import de.jensklingenberg.scratch.Broadcast
import de.jensklingenberg.scratch.ScratchList
import de.jensklingenberg.scratch.Sprite
import de.jensklingenberg.scratch.blockBuilder
import de.jensklingenberg.scratch.common.createBlocks23
import de.jensklingenberg.scratch.control.createCloneOf
import de.jensklingenberg.scratch.control.forever
import de.jensklingenberg.scratch.data.deleteAllOf
import de.jensklingenberg.scratch.event.whenFlagClicked
import de.jensklingenberg.scratch.event.whenIRecieve
import de.jensklingenberg.scratch.looks.say
import de.jensklingenberg.scratch.model.Target
import de.jensklingenberg.scratch.model.createTarget
import de.jensklingenberg.scratch.motion.ifOnEdgeBounce
import de.jensklingenberg.scratch.motion.move
import de.jensklingenberg.scratch.motion.pointInDirection
import de.jensklingenberg.scratch.motion.pointTowards
import de.jensklingenberg.scratch.motion.turnLeft
import de.jensklingenberg.scratch.motion.turnRight
import de.jensklingenberg.scratch.operator.OperatorContains
import de.jensklingenberg.scratch.procedures.ArgumentBoolean
import de.jensklingenberg.scratch.procedures.ArgumentString
import de.jensklingenberg.scratch.procedures.Input
import de.jensklingenberg.scratch.procedures.call
import de.jensklingenberg.scratch.procedures.definition


fun MySprite(jensList: ScratchList): Target {
    val elements = Input(ArgumentBoolean("bool"))
    val elements1 = Input(ArgumentString("bool2"))

    val sprite = Sprite(
        "Sprite1", listOf(
            costume1,
            costum2
        ), listOf(
            sound1
        )
    )
    val broadcast = Broadcast("hello")

    val list = blockBuilder {
        whenIRecieve(broadcast)
        say("Hello")
    }

    val list2 = blockBuilder {
        whenFlagClicked()
        createCloneOf("Hey")
        ifOnEdgeBounce()
        turnLeft(15)
        turnRight(15)
        deleteAllOf(jensList)
        pointTowards("_mouse_")
        pointInDirection(90)
        move(10)
        call("Hey", listOf(elements))
        forever {
            say(OperatorContains("Hello", "H"))
        }
    }


    val list3 = blockBuilder {

        definition("Hey", true, listOf(elements))
        say(elements1.argument)
    }

    val blockMap = createBlocks23(listOf(list2, list3, list))

    return createTarget(blockMap, sprite, emptyList(), listOf(jensList))
}