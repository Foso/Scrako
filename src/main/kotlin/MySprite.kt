package me.jens

import de.jensklingenberg.scratch.Broadcast
import de.jensklingenberg.scratch.ScratchList
import de.jensklingenberg.scratch.Sprite
import de.jensklingenberg.scratch.blockBuilder
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.createBlocks23
import de.jensklingenberg.scratch.control.IfE
import de.jensklingenberg.scratch.control.case
import de.jensklingenberg.scratch.control.createCloneOf
import de.jensklingenberg.scratch.control.forever
import de.jensklingenberg.scratch.control.ifElse
import de.jensklingenberg.scratch.control.ife
import de.jensklingenberg.scratch.control.switch
import de.jensklingenberg.scratch.data.deleteAllOf
import de.jensklingenberg.scratch.event.whenFlagClicked
import de.jensklingenberg.scratch.event.whenIRecieve
import de.jensklingenberg.scratch.looks.LooksSayContent
import de.jensklingenberg.scratch.looks.say
import de.jensklingenberg.scratch.model.Target
import de.jensklingenberg.scratch.model.createTarget
import de.jensklingenberg.scratch.motion.RotationStyle.ALL_AROUND
import de.jensklingenberg.scratch.motion.glideToXY
import de.jensklingenberg.scratch.motion.ifOnEdgeBounce
import de.jensklingenberg.scratch.motion.move
import de.jensklingenberg.scratch.motion.pointInDirection
import de.jensklingenberg.scratch.motion.pointTowards
import de.jensklingenberg.scratch.motion.setRotationStyle
import de.jensklingenberg.scratch.motion.turnLeft
import de.jensklingenberg.scratch.motion.turnRight
import de.jensklingenberg.scratch.operator.OperatorContains
import de.jensklingenberg.scratch.operator.contains
import de.jensklingenberg.scratch.operator.gt
import de.jensklingenberg.scratch.operator.lt
import de.jensklingenberg.scratch.procedures.ArgumentBoolean
import de.jensklingenberg.scratch.procedures.ArgumentString
import de.jensklingenberg.scratch.procedures.Input
import de.jensklingenberg.scratch.procedures.call
import de.jensklingenberg.scratch.procedures.definition
import de.jensklingenberg.scratch.sensing.Answer
import de.jensklingenberg.scratch.sensing.askAndWait


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
        setRotationStyle(ALL_AROUND)
        glideToXY(1.0, 2.0, 3.0)
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
        move(10.0)
        say(gt(1, 3))
        call("Hey", listOf(elements))
        askAndWait(LooksSayContent.Literal("Wie gehts?"))
        switch {
            case(contains("heel","ddd")){
                say(Answer())
            }
            case(lt(1,3)){
                say("Bye")
            }
            case(gt(1,5)){
                say("Bye")
            }
            case(gt(3,5)){

            }
        }
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

private fun NodeBuilder.fff() {
    switch {
        case(contains("heel", "ddd")) {
            say(Answer())
        }
        case(lt(1, 3)) {
            say("Bye")
        }
        case(gt(1, 5)) {
            say("Bye")
        }
        case(gt(3, 5)) {
            say("Bye")
        }
    }
}