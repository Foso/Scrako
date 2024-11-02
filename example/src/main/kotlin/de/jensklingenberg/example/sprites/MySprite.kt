package de.jensklingenberg.example.sprites

import de.jensklingenberg.example.costumes._tile15
import de.jensklingenberg.example.costumes.costume2n
import de.jensklingenberg.scrako.builder.ProjectBuilder
import de.jensklingenberg.scrako.builder.addCostumes
import de.jensklingenberg.scrako.builder.createBroadcast
import de.jensklingenberg.scrako.builder.getOrCreateList
import de.jensklingenberg.scrako.builder.getOrCreateVariable
import de.jensklingenberg.scrako.builder.scriptBuilder
import de.jensklingenberg.scrako.builder.spriteBuilder
import de.jensklingenberg.scrako.common.Broadcast
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scrako.common.ScratchVariable
import de.jensklingenberg.scratch3.control.forever
import de.jensklingenberg.scratch3.control.ifThen
import de.jensklingenberg.scratch3.control.repeat
import de.jensklingenberg.scratch3.data.changeVariableBy
import de.jensklingenberg.scratch3.data.hideList
import de.jensklingenberg.scratch3.data.itemOfXList
import de.jensklingenberg.scratch3.data.lengthOfList
import de.jensklingenberg.scratch3.data.replaceItemOfListWith
import de.jensklingenberg.scratch3.data.setVariable
import de.jensklingenberg.scratch3.data.showList
import de.jensklingenberg.scratch3.event.Key
import de.jensklingenberg.scratch3.event.Key.DOWN_ARROW
import de.jensklingenberg.scratch3.event.Key.LEFT_ARROW
import de.jensklingenberg.scratch3.event.Key.RIGHT_ARROW
import de.jensklingenberg.scratch3.event.Key.UP_ARROW
import de.jensklingenberg.scratch3.event.sendBroadcast
import de.jensklingenberg.scratch3.event.whenFlagClicked
import de.jensklingenberg.scratch3.event.whenIReceiveBroadcast
import de.jensklingenberg.scratch3.event.whenKeyPress
import de.jensklingenberg.scratch3.extension.pen.eraseAll
import de.jensklingenberg.scratch3.extension.pen.stamp
import de.jensklingenberg.scratch3.looks.hide
import de.jensklingenberg.scratch3.motion.changeYby
import de.jensklingenberg.scratch3.motion.move
import de.jensklingenberg.scratch3.motion.setX
import de.jensklingenberg.scratch3.motion.switchCostume
import de.jensklingenberg.scratch3.operator.and
import de.jensklingenberg.scratch3.operator.div
import de.jensklingenberg.scratch3.operator.gt
import de.jensklingenberg.scratch3.operator.lt
import de.jensklingenberg.scratch3.operator.minus
import de.jensklingenberg.scratch3.operator.plus
import de.jensklingenberg.scratch3.operator.times
import de.jensklingenberg.scratch3.procedures.call
import de.jensklingenberg.scratch3.procedures.define
import de.jensklingenberg.scratch3.sensing.keyIsPressed
import debugger.log
import gotoxy

const val PlayerIconID = "2"
const val BackgroundIconId = "1"
const val array_width = 11
const val array_height = 8
const val DEBUG = true
const val X_START = -240
const val Y_START = 150
const val MOVE_DISTANCE = 40

fun ProjectBuilder.MySprite1(paint: Broadcast) {
    val input = createBroadcast("input")

    spriteBuilder("Sprite123") {
        val jens2 = getOrCreateList(
            "jens2",
            (0..88).map { "1" }
        )

        addCostumes(listOf(_tile15, costume2n))

        val playerX = getOrCreateVariable("playerX")
        val playerY = getOrCreateVariable("playerY")
        val width = getOrCreateVariable("width")

        /**
         * Start
         */
        scriptBuilder {
            whenFlagClicked()
            hide()
            replaceItemOfListWith(getIndexOf(0, 0), jens2, PlayerIconID)
            setVariable(playerX, 1)
            setVariable(playerY, 1)
            //log(playerX)
            forever {
                sendBroadcast(paint)
                sendBroadcast(input)
            }
        }

        /**
         * Paint
         */
        scriptBuilder {
            whenIReceiveBroadcast(paint)
            call("paint1")
        }

        scriptBuilder {
            val paintY = getOrCreateVariable("paint_yy")
            val paintX = getOrCreateVariable("paint_xx")

            define("paint1", withoutRefresh = true) {
                hide()
                setVariable(width, array_width)
                setVariable(paintY, 0)
                eraseAll()
                gotoxy(X_START, Y_START)
                repeat(lengthOfList(jens2) / width) {
                    setX(X_START)
                    setVariable(paintX, 0)
                    repeat(width) {
                        switchCostume(itemOffArray(paintY, paintX, jens2))
                        move(MOVE_DISTANCE)
                        stamp()
                        changeVariableBy(paintX, 1)
                    }
                    changeVariableBy(paintY, 1)
                    changeYby(-MOVE_DISTANCE)
                }
            }
        }

        /**
         * Input
         */
        scriptBuilder {
            whenIReceiveBroadcast(input)
            ifThen(keyIsPressed(RIGHT_ARROW) and (playerX lt (width minus 1))) {
                    replaceItemOfListWith(getIndexOf(playerY, playerX), jens2, BackgroundIconId)
                    changeVariableBy(playerX, 1)
                    replaceItemOfListWith(getIndexOf(playerY, playerX), jens2, PlayerIconID)
                    //log(playerX)
            }

            ifThen(keyIsPressed(LEFT_ARROW)) {
                ifThen(playerX gt IntBlock(0)) {
                    replaceItemOfListWith(getIndexOf(playerY, playerX), jens2, BackgroundIconId)
                    changeVariableBy(playerX, -1)
                    replaceItemOfListWith(getIndexOf(playerY, playerX), jens2, PlayerIconID)
                }
            }

            ifThen(keyIsPressed(DOWN_ARROW)) {
                ifThen(playerY lt IntBlock(array_height -1)) {
                    //log(playerY)
                    replaceItemOfListWith(getIndexOf(playerY, playerX), jens2, BackgroundIconId)
                    changeVariableBy(playerY, 1)
                    replaceItemOfListWith(getIndexOf(playerY, playerX), jens2, PlayerIconID)
                }
            }

            ifThen(keyIsPressed(UP_ARROW)) {
                ifThen(playerY gt IntBlock(0)) {
                    replaceItemOfListWith(getIndexOf(playerY, playerX), jens2, BackgroundIconId)
                    changeVariableBy(playerY, -1)
                    replaceItemOfListWith(getIndexOf(playerY, playerX), jens2, PlayerIconID)
                }
            }
        }

        scriptBuilder {
            whenKeyPress(Key.D)
            showList(jens2)
        }

        scriptBuilder {
            whenKeyPress(Key.E)
            hideList(jens2)
        }

    }
}

fun getIndexOf(playerY: ScratchVariable, playerX: ScratchVariable): ReporterBlock {
    return playerY times IntBlock(array_width) plus (playerX plus 1)
}

private fun itemOffArray(
    yy: ScratchVariable,
    xx: ScratchVariable,
    jens2: ScratchList
): ReporterBlock {
    return itemOfXList(yy times IntBlock(array_width) plus (xx plus 1), jens2)
}

private fun getIndexOf(
    yy: IntBlock,
    xx: IntBlock
): ReporterBlock {
    val newBlock = IntBlock(xx.value + 1)
    return yy times IntBlock(array_width) plus (newBlock)
}

private fun getIndexOf(
    yy: Int,
    xx: Int
): ReporterBlock {
    return getIndexOf(IntBlock(yy), IntBlock(xx))
}


