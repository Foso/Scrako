package me.jens

import de.jensklingenberg.scratch.Broadcast
import de.jensklingenberg.scratch.ScratchList
import de.jensklingenberg.scratch.Sprite
import de.jensklingenberg.scratch.blockBuilder
import de.jensklingenberg.scratch.common.createBlocks23
import de.jensklingenberg.scratch.control.createCloneOf
import de.jensklingenberg.scratch.event.whenFlagClicked
import de.jensklingenberg.scratch.looks.Say
import de.jensklingenberg.scratch.model.Target
import de.jensklingenberg.scratch.model.createTarget
import de.jensklingenberg.scratch.motion.ifOnEdgeBounce
import de.jensklingenberg.scratch.motion.turnLeft
import de.jensklingenberg.scratch.motion.turnRight
import de.jensklingenberg.scratch.procedures.Definition
import de.jensklingenberg.scratch.sensing.DaysSince2000



fun MySprite(jensList: ScratchList): Target {

    val sprite = Sprite(
        "Sprite1", listOf(
            costume1,
            costum2
        ), listOf(
            sound1
        )
    )
    val broadcast = Broadcast("hello")


    val list2 = blockBuilder {
        whenFlagClicked()
        createCloneOf("Hey")
        ifOnEdgeBounce()
        turnLeft(15)
        turnRight(15)
    }

    val list3 = listOf(
        Definition("Jens"),
        Say(DaysSince2000())
    )

    val blockMap = createBlocks23(listOf(list2))

    return createTarget(blockMap, sprite, emptyList(), listOf(jensList))
}