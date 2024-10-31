package me.jens.targets

import de.jensklingenberg.scrako.builder.ProjectBuilder
import de.jensklingenberg.scrako.builder.addCostumes
import de.jensklingenberg.scrako.builder.getOrCreateList
import de.jensklingenberg.scrako.builder.getOrCreateVariable
import de.jensklingenberg.scrako.builder.scriptBuilder
import de.jensklingenberg.scrako.builder.spriteBuilder
import de.jensklingenberg.scrako.common.Broadcast
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scrako.common.ScratchVariable
import de.jensklingenberg.scrako.model.Costume
import de.jensklingenberg.scratch.control.forever
import de.jensklingenberg.scratch.control.ifThen
import de.jensklingenberg.scratch.control.repeat
import de.jensklingenberg.scratch.data.changeVariableBy
import de.jensklingenberg.scratch.data.hideList
import de.jensklingenberg.scratch.data.itemOfXList
import de.jensklingenberg.scratch.data.lengthOfList
import de.jensklingenberg.scratch.data.replaceItemOfListWith
import de.jensklingenberg.scratch.data.setVariable
import de.jensklingenberg.scratch.data.showList
import de.jensklingenberg.scratch.event.Key
import de.jensklingenberg.scratch.event.sendBroadcast
import de.jensklingenberg.scratch.event.whenFlagClicked
import de.jensklingenberg.scratch.event.whenIReceiveBroadcast
import de.jensklingenberg.scratch.event.whenKeyPress
import de.jensklingenberg.scratch.extension.pen.eraseAll
import de.jensklingenberg.scratch.extension.pen.stamp
import de.jensklingenberg.scratch.looks.hide
import de.jensklingenberg.scratch.motion.DragMode
import de.jensklingenberg.scratch.motion.changeYby
import de.jensklingenberg.scratch.motion.move
import de.jensklingenberg.scratch.motion.setDragMode
import de.jensklingenberg.scratch.motion.setx
import de.jensklingenberg.scratch.motion.switchCostume
import de.jensklingenberg.scratch.operator.div
import de.jensklingenberg.scratch.operator.plus
import de.jensklingenberg.scratch.operator.times
import de.jensklingenberg.scratch.procedures.call
import de.jensklingenberg.scratch.procedures.define
import de.jensklingenberg.scratch.sensing.keyIsPressed
import debugger.log
import gotoxy


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
    dataFormat = "png",
    assetId = "0eabdcd3cdfeea01bb6ff8d3ad5fe301",
    md5ext = "0eabdcd3cdfeea01bb6ff8d3ad5fe301.png",
    rotationCenterX = 31.0,
    rotationCenterY = 26.0
)

const val PlayerIconID = "2"
const val BackgroundIconId = "1"
const val array_width = 11
const val DEBUG = true


fun ProjectBuilder.MySprite1(paint: Broadcast, input: Broadcast) {

    val X_START = -230
    val Y_START = 120
    val MOVE_DISTANCE = 40
    spriteBuilder("Sprite1") {
        addCostumes(listOf(costume1n, costume2n))

        val jens2 = getOrCreateList(
            "jens2",
            (0..84).map { "1" }
        )
        val playerX = getOrCreateVariable("playerX")
        val playerY = getOrCreateVariable("playerY")
        val width = getOrCreateVariable("width")
        val yy = getOrCreateVariable("paint_yy")
        val xx = getOrCreateVariable("paint_xx")

        /**
         * Start
         */
        scriptBuilder {
            whenFlagClicked()
            replaceItemOfListWith(getIndexOf(0, 0), jens2, PlayerIconID)
            setVariable(playerX, 1)
            setVariable(playerY, 1)
            log(playerX)
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
            setDragMode(DragMode.NOT_DRAG)
            call("paint1")
        }

        scriptBuilder {
            define("paint1", withoutRefresh = true) {
                hide()
                setVariable(width, array_width)
                setVariable(yy, 0)
                eraseAll()
                gotoxy(X_START, Y_START)
                repeat(lengthOfList(jens2) / width) {
                    setx(X_START)
                    setVariable(xx, 0)
                    repeat(width) {
                        switchCostume(itemOffArray(yy, xx, jens2))
                        move(MOVE_DISTANCE)
                        stamp()
                        changeVariableBy(xx, 1)
                    }
                    changeVariableBy(yy, 1)
                    changeYby(-MOVE_DISTANCE)
                }
            }
        }

        /**
         * Input
         */
        scriptBuilder {
            whenIReceiveBroadcast(input)
            ifThen(keyIsPressed(Key.RIGHT_ARROW)) {
                replaceItemOfListWith(getIndexOf(playerY, playerX), jens2, BackgroundIconId)
                changeVariableBy(playerX, 1)
                replaceItemOfListWith(getIndexOf(playerY, playerX), jens2, PlayerIconID)
            }

            ifThen(keyIsPressed(Key.LEFT_ARROW)) {
                replaceItemOfListWith(getIndexOf(playerY, playerX), jens2, BackgroundIconId)
                changeVariableBy(playerX, -1)
                replaceItemOfListWith(getIndexOf(playerY, playerX), jens2, PlayerIconID)
            }

            ifThen(keyIsPressed(Key.DOWN_ARROW)) {

                replaceItemOfListWith(getIndexOf(playerY, playerX), jens2, BackgroundIconId)
                changeVariableBy(playerY, 1)
                replaceItemOfListWith(getIndexOf(playerY, playerX), jens2, PlayerIconID)
            }

            ifThen(keyIsPressed(Key.UP_ARROW)) {
                replaceItemOfListWith(getIndexOf(playerY, playerX), jens2, BackgroundIconId)
                changeVariableBy(playerY, -1)
                replaceItemOfListWith(getIndexOf(playerY, playerX), jens2, PlayerIconID)
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


