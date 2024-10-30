package me.jens.targets

import de.jensklingenberg.scrako.builder.ProjectBuilder
import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.builder.addCostumes
import de.jensklingenberg.scrako.builder.addSprite
import de.jensklingenberg.scrako.builder.createBroadcast
import de.jensklingenberg.scrako.builder.getOrCreateList
import de.jensklingenberg.scrako.builder.getOrCreateVariable
import de.jensklingenberg.scrako.builder.scriptBuilder
import de.jensklingenberg.scrako.builder.spriteBuilder
import de.jensklingenberg.scrako.common.Argument
import de.jensklingenberg.scrako.common.ArgumentType
import de.jensklingenberg.scrako.common.BooleanBlock
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.StringBlock
import de.jensklingenberg.scrako.model.Costume
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
import de.jensklingenberg.scratch.event.whenIReceiveBroadcast
import de.jensklingenberg.scratch.event.whenStartAsClone
import de.jensklingenberg.scratch.extension.pen.eraseAll
import de.jensklingenberg.scratch.extension.pen.stamp
import de.jensklingenberg.scratch.looks.goToFront
import de.jensklingenberg.scratch.looks.hide
import de.jensklingenberg.scratch.looks.say
import de.jensklingenberg.scratch.motion.changeXby
import de.jensklingenberg.scratch.motion.changeYby
import de.jensklingenberg.scratch.motion.setx
import de.jensklingenberg.scratch.motion.switchCostume
import de.jensklingenberg.scratch.operator.Add
import de.jensklingenberg.scratch.operator.Subtract
import de.jensklingenberg.scratch.operator.and
import de.jensklingenberg.scratch.operator.div
import de.jensklingenberg.scratch.operator.gt
import de.jensklingenberg.scratch.operator.lt
import de.jensklingenberg.scratch.operator.minus
import de.jensklingenberg.scratch.operator.times
import de.jensklingenberg.scratch.procedures.call
import de.jensklingenberg.scratch.procedures.define
import de.jensklingenberg.scratch.sensing.keyIsPressed
import debugger.error
import debugger.log
import debugger.warn
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

const val PlayerIconID = 1
const val BackgroundIconId = 2

fun ProjectBuilder.MyTarget() {

    val move = createBroadcast("move")
    val paint = createBroadcast("paint")
    val input = createBroadcast("input")

    spriteBuilder("Sprite2") {
        addSprite(spriteArrow)
        addCostumes(listOf(costume1n, costume2n))
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

            whenIReceiveBroadcast(input)
            say(playerIndex)
            ifThen(
                keyIsPressed(Key.RIGHT_ARROW) and (playerIndex lt lengthOfList(jens2))
            ) {
                replaceItemOfWith(playerIndex, jens2, PlayerIconID)
                changeVariableBy(playerIndex, 1)
                replaceItemOfWith(playerIndex, jens2, BackgroundIconId)
            }
            ifThen(keyIsPressed(Key.LEFT_ARROW) and (playerIndex gt 1)) {
                replaceItemOfWith(playerIndex, jens2, PlayerIconID)
                changeVariableBy(playerIndex, -1)
                replaceItemOfWith(playerIndex, jens2, BackgroundIconId)
            }

            ifThen((keyIsPressed(Key.DOWN_ARROW)) and (playerIndex lt lengthOfList(jens2) - (width ))) {
                replaceItemOfWith(playerIndex, jens2, 1)
                changeVariableBy(playerIndex, width)
                replaceItemOfWith(playerIndex, jens2, BackgroundIconId)
            }

            ifThen((keyIsPressed(Key.UP_ARROW)) and (playerIndex gt width)) {
                replaceItemOfWith(playerIndex, jens2, PlayerIconID)
                changeVariableBy(playerIndex, width times IntBlock(-1))
                replaceItemOfWith(playerIndex, jens2, BackgroundIconId)
            }
        }


        scriptBuilder {
            val height = getOrCreateVariable("height")
            whenFlagClicked()
            setVariable(playerIndex, 0)
            replaceItemOfWith(playerIndex, jens2, BackgroundIconId)
            setVariable(height, lengthOfList(jens2) / width)
            say(lengthOfList(jens2) / width)
            forever {
                sendBroadcast(move)
                sendBroadcast(paint)
                sendBroadcast(input)
            }
        }

        scriptBuilder {

            define(
                "pain",
                withoutRefresh = true,
                arguments = listOf(
                    Argument("width", ArgumentType.NUMBER_OR_TEXT),
                )
            ) {
                val height = getOrCreateVariable("height")
                setVariable(width, 6)
                setVariable(height,0)
               // hide()
                setVariable(height, lengthOfList(jens2) / width)
                error(lengthOfList(jens2) / width)
                say(lengthOfList(jens2) / width)
                gotoxy(-220, 160)
                setVariable(width, 6)
                repeat(width){
                    stamp()
                    changeXby(40)
                }


            }
        }

        scriptBuilder {

            define(
                "pain2",
                withoutRefresh = true,
                arguments = listOf(
                    Argument("width", ArgumentType.NUMBER_OR_TEXT),
                )
            ) {
                hide()
                setVariable(width, 6)
                setVariable(gridY, 0)
                eraseAll()
                gotoxy(0, 0)
                goToFront()
                setVariable(listIndex, 1)
                setVariable(gridX, 1)
                repeat(lengthOfList(jens2)) {
                    ifThen(gridX gt width) {
                        setVariable(gridX, 1)
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

        scriptBuilder {
            whenIReceiveBroadcast(paint)
            call("pain", listOf(StringBlock("6")))
        }

    }
}



private operator fun ReporterBlock.plus(i: Int): Add {
    return Add(this, IntBlock(i))
}

private operator fun ReporterBlock.minus(i: Int): ReporterBlock {
    return Subtract(this, IntBlock(-i))
}


private fun ScriptBuilder.hallo(stringBlock: BooleanBlock, stringBlock1: StringBlock) {
    call("Hallo", listOf(stringBlock, stringBlock1))
}


infix operator fun Int.plus(int: ReporterBlock): ReporterBlock {
    return Add(IntBlock(this), int)
}
