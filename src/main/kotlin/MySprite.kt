package me.jens

import de.jensklingenberg.scratch.Broadcast
import de.jensklingenberg.scratch.ScratchList
import de.jensklingenberg.scratch.Sprite
import de.jensklingenberg.scratch.blockBuilder
import de.jensklingenberg.scratch.common.createBlocks23
import de.jensklingenberg.scratch.common.createVariable
import de.jensklingenberg.scratch.control.StopOption
import de.jensklingenberg.scratch.control.case
import de.jensklingenberg.scratch.control.stop
import de.jensklingenberg.scratch.control.switch
import de.jensklingenberg.scratch.control.waitUntil
import de.jensklingenberg.scratch.createList
import de.jensklingenberg.scratch.data.addToList
import de.jensklingenberg.scratch.data.hideList
import de.jensklingenberg.scratch.data.insertAt
import de.jensklingenberg.scratch.data.itemNumOfList
import de.jensklingenberg.scratch.data.lengthOfList
import de.jensklingenberg.scratch.data.listContains
import de.jensklingenberg.scratch.data.replaceItemOfWith
import de.jensklingenberg.scratch.data.setVariable
import de.jensklingenberg.scratch.data.showList
import de.jensklingenberg.scratch.event.whenStartAsClone
import de.jensklingenberg.scratch.looks.say
import de.jensklingenberg.scratch.model.Target
import de.jensklingenberg.scratch.model.createTarget
import de.jensklingenberg.scratch.motion.changeXby
import de.jensklingenberg.scratch.operator.add
import de.jensklingenberg.scratch.procedures.ArgumentBoolean
import de.jensklingenberg.scratch.procedures.ArgumentString
import de.jensklingenberg.scratch.procedures.Input
import de.jensklingenberg.scratch.procedures.definition
import de.jensklingenberg.scratch.sensing.Answer
import de.jensklingenberg.scratch.sensing.colorIsTouchingColor
import de.jensklingenberg.scratch.sensing.touchingColor
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
        val tt = createVariable("myVariable2")
        val users = createList("Users", listOf("Jens", "Martin", "Thomas"))
        say(lengthOfList(users))
        addToList(add(3,3), users)
        //insertAt("Jens", users, 1)
        insertAt(add(44,33), users, add(3,2))
        replaceItemOfWith(add(3,4), users, add(3,2))
        setVariable(tt, add(3,4))
        say(listContains(users, add(3,4)))
        showList(users)
        hideList(users)
        say(touchingColor("#1f9226"))
        stop(StopOption.ALL)

    }

    val list2 = whenFlagClicked(jensList, elements)

    val list3 = blockBuilder {

        definition("Hey", true, listOf(elements))
        say(elements1.argument)
    }

    val blockMap = createBlocks23(listOf(list.childs))

    return createTarget(blockMap, sprite, emptyList(), setOf(jensList) + list.lists, list.variables)
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
