package me.jens

import me.jens.scratch.Target
import me.jens.scratch.common.createBlocks23
import me.jens.scratch.control.Repeat
import me.jens.scratch.createTarget
import me.jens.scratch.data.ItemOfXList
import me.jens.scratch.event.Key
import me.jens.scratch.event.SendBroadcastAndWait
import me.jens.scratch.event.WhenFlagClicked
import me.jens.scratch.event.WhenKeyPress
import me.jens.scratch.looks.GoToFront
import me.jens.scratch.looks.Say
import me.jens.scratch.motion.SwitchCostume
import me.jens.scratch.operator.LetterOf
import me.jens.scratch.procedures.Call
import me.jens.scratch.procedures.Definition
import me.jens.scratch.sensing.DaysSince2000
import scratch.Broadcast
import scratch.ScratchList
import scratch.Sprite

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

    val list1 = listOf(
        WhenKeyPress(Key.TWO),
        GoToFront(),
        SendBroadcastAndWait(broadcast),
        Repeat(
            10,
            SwitchCostume(ItemOfXList(1, jensList))
        )
    )

    val list2 = listOf(
        WhenFlagClicked(),
        Call("Jens"),
    )

    val list3 = listOf(
        Definition("Jens"),
        Say(DaysSince2000())
    )

    val list4 = listOf(Say(LetterOf(3, "abc")))
    val test = createBlocks23(listOf(list1, list3, list2, list4))

    return createTarget(test, sprite, emptyList(), listOf(jensList))
}