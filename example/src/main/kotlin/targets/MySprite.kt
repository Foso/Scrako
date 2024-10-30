package me.jens.targets

import de.jensklingenberg.scrako.builder.ProjectBuilder
import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.builder.TargetBuilder
import de.jensklingenberg.scrako.builder.addCostume
import de.jensklingenberg.scrako.builder.addSprite
import de.jensklingenberg.scrako.builder.createBroadcast
import de.jensklingenberg.scrako.builder.getOrCreateList
import de.jensklingenberg.scrako.builder.getOrCreateVariable
import de.jensklingenberg.scrako.builder.scriptBuilder
import de.jensklingenberg.scrako.builder.targetBuilder
import de.jensklingenberg.scrako.common.BooleanBlock
import de.jensklingenberg.scrako.model.Costume
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scrako.common.ScratchVariable
import de.jensklingenberg.scrako.common.StringBlock
import de.jensklingenberg.scratch.control.forever
import de.jensklingenberg.scratch.control.ifThen
import de.jensklingenberg.scratch.control.repeat
import de.jensklingenberg.scratch.data.changeVariableBy
import de.jensklingenberg.scratch.data.itemOfXList
import de.jensklingenberg.scratch.data.lengthOfList
import de.jensklingenberg.scratch.data.replaceItemOfWith
import de.jensklingenberg.scratch.data.setVariable
import de.jensklingenberg.scratch.event.Key
import de.jensklingenberg.scratch.event.sendBroadcast
import de.jensklingenberg.scratch.event.whenFlagClicked
import de.jensklingenberg.scratch.event.whenIRecieve
import de.jensklingenberg.scratch.extension.pen.eraseAll
import de.jensklingenberg.scratch.extension.pen.stamp
import de.jensklingenberg.scratch.looks.goToFront
import de.jensklingenberg.scratch.looks.say
import de.jensklingenberg.scratch.motion.changeXby
import de.jensklingenberg.scratch.motion.changeYby
import de.jensklingenberg.scratch.motion.setx
import de.jensklingenberg.scratch.motion.switchCostume
import de.jensklingenberg.scratch.operator.Add
import de.jensklingenberg.scratch.operator.Multiply
import de.jensklingenberg.scratch.operator.and
import de.jensklingenberg.scratch.operator.gt
import de.jensklingenberg.scratch.operator.lt
import de.jensklingenberg.scratch.operator.minus
import de.jensklingenberg.scratch.procedures.call
import de.jensklingenberg.scratch.procedures.define
import de.jensklingenberg.scratch.sensing.keyIsPressed
import gotoxy
import me.jens.spriteArrow


val costume1n = Costume(
    name = "costume1",
    bitmapResolution = 2,
    dataFormat = "png",
    assetId = "e4a6106fe45d48f3e4bd5b7529e6fb48",
    md5ext = "e4a6106fe45d48f3e4bd5b7529e6fb48.png",
    rotationCenterX = 31.0,
    rotationCenterY = 26.0
)

val costume2n = Costume(
    name = "costume2",
    bitmapResolution = 2,
    dataFormat = "svg",
    assetId = "d223dc2f76fb48d01503c140bccf5e0e",
    md5ext = "d223dc2f76fb48d01503c140bccf5e0e.svg",
    rotationCenterX = 31.0,
    rotationCenterY = 26.0
)


fun ProjectBuilder.MyTarget() {
    val move = createBroadcast("move")
    val paint = createBroadcast("paint")
    val input = createBroadcast("input")

    targetBuilder("Sprite2") {
        addSprite(spriteArrow)
        addCostume(costume1n)
        addCostume(costume2n)
        val gridY = getOrCreateVariable("gridY")
        val gridX = getOrCreateVariable("gridX")
        val jens2 = getOrCreateList(
            "jens2",
            listOf(
                "2",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1"
            )
        )
        val playerIndex = getOrCreateVariable("playerIndex")
        val width = getOrCreateVariable("width")
        val listIndex = getOrCreateVariable("listIndex")

        scriptBuilder {

            whenIRecieve(input)
            say(2 + playerIndex)
            ifThen(
                keyIsPressed(Key.RIGHT_ARROW) and (playerIndex lt lengthOfList(jens2))
            ) {
                replaceItemOfWith(playerIndex, jens2, IntBlock(1))
                changeVariableBy(playerIndex, 1)
                replaceItemOfWith(playerIndex, jens2, IntBlock(2))
            }
            ifThen(keyIsPressed(Key.LEFT_ARROW) and (playerIndex gt 1)) {
                replaceItemOfWith(playerIndex, jens2, IntBlock(1))
                changeVariableBy(playerIndex, -1)
                replaceItemOfWith(playerIndex, jens2, IntBlock(2))
            }

            ifThen(keyIsPressed(Key.DOWN_ARROW)) {
                ifThen(playerIndex lt lengthOfList(jens2) - width) {
                    replaceItemOfWith(playerIndex, jens2, IntBlock(1))
                    changeVariableBy(playerIndex, width)
                    replaceItemOfWith(playerIndex, jens2, IntBlock(2))
                }
            }

            ifThen(keyIsPressed(Key.UP_ARROW)) {
                ifThen((playerIndex gt width)) {
                    replaceItemOfWith(playerIndex, jens2, IntBlock(1))
                    changeVariableBy(playerIndex, Multiply(width, IntBlock(-1)))
                    replaceItemOfWith(playerIndex, jens2, IntBlock(2))
                }
            }
        }

        scriptBuilder {
            whenFlagClicked()
            setVariable(playerIndex, IntBlock(1))
            replaceItemOfWith(playerIndex, jens2, IntBlock(2))
            forever {
                sendBroadcast(move)
                sendBroadcast(paint)
                sendBroadcast(input)
            }
        }

        definePaint(width, gridY, listIndex, gridX, jens2)

        scriptBuilder {
            whenIRecieve(paint)
            call("pain", listOf(IntBlock(3)))
        }

    }
}


private fun TargetBuilder.definePaint(
    width: ScratchVariable,
    gridY: ScratchVariable,
    listIndex: ScratchVariable,
    gridX: ScratchVariable,
    jens2: ScratchList
) {
    scriptBuilder {

        define(
            "pain",
            withoutRefresh = true
        ) {
            setVariable(width, IntBlock(6))
            setVariable(gridY, IntBlock(0))
            eraseAll()
            gotoxy(IntBlock(0), IntBlock(0))
            goToFront()
            setVariable(listIndex, IntBlock(1))
            setVariable(gridX, IntBlock(1))
            repeat(lengthOfList(jens2)) {
                ifThen(gridX gt width) {
                    setVariable(gridX, IntBlock(1))
                    changeYby(-40)
                    setx(0)
                }
                switchCostume(itemOfXList(listIndex, jens2))
                stamp()
                changeXby(40)
                changeVariableBy(gridX, 1)
                changeVariableBy(listIndex, 1)
            }
        }
    }
}

private operator fun ReporterBlock.plus(intBlock: ReporterBlock): Add {
   return Add(this, intBlock)
}

private fun ScriptBuilder.hallo(stringBlock: BooleanBlock, stringBlock1: StringBlock) {
    call("Hallo", listOf(stringBlock, stringBlock1))
}



infix operator fun Int.plus(int: ReporterBlock): ReporterBlock {
    return Add(IntBlock(this), int)
}
