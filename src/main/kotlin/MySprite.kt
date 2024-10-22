package me.jens

import de.jensklingenberg.scratch.common.createBlocks23
import de.jensklingenberg.scratch.control.Repeat
import de.jensklingenberg.scratch.createTarget
import de.jensklingenberg.scratch.data.ItemOfXList
import de.jensklingenberg.scratch.event.Key
import de.jensklingenberg.scratch.event.SendBroadcastAndWait
import de.jensklingenberg.scratch.event.WhenFlagClicked
import de.jensklingenberg.scratch.event.WhenKeyPress
import de.jensklingenberg.scratch.looks.GoToFront
import de.jensklingenberg.scratch.looks.Say
import de.jensklingenberg.scratch.motion.SwitchCostume
import de.jensklingenberg.scratch.operator.LetterOf
import de.jensklingenberg.scratch.procedures.Call
import de.jensklingenberg.scratch.procedures.Definition
import de.jensklingenberg.scratch.sensing.DaysSince2000

fun MySprite(jensList: de.jensklingenberg.scratch.ScratchList): de.jensklingenberg.scratch.Target {

    val sprite = de.jensklingenberg.scratch.Sprite(
        "Sprite1", listOf(
            costume1,
            costum2
        ), listOf(
            sound1
        )
    )
    val broadcast = de.jensklingenberg.scratch.Broadcast("hello")

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