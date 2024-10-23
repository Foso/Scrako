package me.jens

import de.jensklingenberg.scratch.Broadcast
import de.jensklingenberg.scratch.ScratchList
import de.jensklingenberg.scratch.Sprite
import de.jensklingenberg.scratch.blockBuilder
import de.jensklingenberg.scratch.common.createBlocks23
import de.jensklingenberg.scratch.event.whenFlagClicked
import de.jensklingenberg.scratch.event.whenIRecieve
import de.jensklingenberg.scratch.looks.Size
import de.jensklingenberg.scratch.looks.say
import de.jensklingenberg.scratch.model.Target
import de.jensklingenberg.scratch.model.createTarget
import de.jensklingenberg.scratch.motion.Direction
import de.jensklingenberg.scratch.motion.RotationStyle.ALL_AROUND
import de.jensklingenberg.scratch.motion.XPosition
import de.jensklingenberg.scratch.motion.YPosition
import de.jensklingenberg.scratch.motion.changeXby
import de.jensklingenberg.scratch.motion.glideToXY
import de.jensklingenberg.scratch.motion.setRotationStyle
import de.jensklingenberg.scratch.operator.add
import de.jensklingenberg.scratch.operator.random
import de.jensklingenberg.scratch.procedures.ArgumentBoolean
import de.jensklingenberg.scratch.procedures.ArgumentString
import de.jensklingenberg.scratch.procedures.Input
import de.jensklingenberg.scratch.procedures.definition
import de.jensklingenberg.scratch.sensing.Answer
import de.jensklingenberg.scratch.sensing.Loudness
import de.jensklingenberg.scratch.sensing.MouseDown
import de.jensklingenberg.scratch.sensing.MouseX
import de.jensklingenberg.scratch.sensing.MouseY
import de.jensklingenberg.scratch.sensing.Timer
import de.jensklingenberg.scratch.sensing.Username
import de.jensklingenberg.scratch.sensing.Volume
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
    say(XPosition)
    say(YPosition)
    say(Direction)
    say(Size)
    say(Volume)
    say(Answer)
    say(Loudness)
    say(Timer)
    say(Username)
}
