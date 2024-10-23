package me.jens

import de.jensklingenberg.scratch.Broadcast
import de.jensklingenberg.scratch.ScratchList
import de.jensklingenberg.scratch.Sprite
import de.jensklingenberg.scratch.blockBuilder
import de.jensklingenberg.scratch.common.createBlocks23
import de.jensklingenberg.scratch.control.case
import de.jensklingenberg.scratch.control.createCloneOf
import de.jensklingenberg.scratch.control.forever
import de.jensklingenberg.scratch.control.switch
import de.jensklingenberg.scratch.data.deleteAllOf
import de.jensklingenberg.scratch.event.whenFlagClicked
import de.jensklingenberg.scratch.event.whenIRecieve
import de.jensklingenberg.scratch.looks.LooksSayContent.Literal
import de.jensklingenberg.scratch.looks.say
import de.jensklingenberg.scratch.model.Target
import de.jensklingenberg.scratch.model.createTarget
import de.jensklingenberg.scratch.motion.RotationStyle.ALL_AROUND
import de.jensklingenberg.scratch.motion.changeXby
import de.jensklingenberg.scratch.motion.glideToXY
import de.jensklingenberg.scratch.motion.ifOnEdgeBounce
import de.jensklingenberg.scratch.motion.move
import de.jensklingenberg.scratch.motion.pointInDirection
import de.jensklingenberg.scratch.motion.pointTowards
import de.jensklingenberg.scratch.motion.setRotationStyle
import de.jensklingenberg.scratch.motion.turnLeft
import de.jensklingenberg.scratch.motion.turnRight
import de.jensklingenberg.scratch.operator.OperatorContains
import de.jensklingenberg.scratch.operator.PickRandom
import de.jensklingenberg.scratch.operator.add
import de.jensklingenberg.scratch.operator.contains
import de.jensklingenberg.scratch.operator.gt
import de.jensklingenberg.scratch.operator.lt
import de.jensklingenberg.scratch.procedures.ArgumentBoolean
import de.jensklingenberg.scratch.procedures.ArgumentString
import de.jensklingenberg.scratch.procedures.Input
import de.jensklingenberg.scratch.procedures.call
import de.jensklingenberg.scratch.procedures.definition
import de.jensklingenberg.scratch.sensing.Answer
import de.jensklingenberg.scratch.sensing.Username
import de.jensklingenberg.scratch.sensing.askAndWait
import de.jensklingenberg.scratch.sensing.resetTime


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
        say(PickRandom(1, 10))
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
    say(Username)
    resetTime()
    changeXby(50.0)
}
