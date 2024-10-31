package me.jens.targets

import de.jensklingenberg.scrako.builder.ProjectBuilder
import de.jensklingenberg.scrako.builder.addCostumes
import de.jensklingenberg.scrako.builder.addSprite
import de.jensklingenberg.scrako.builder.createBroadcast
import de.jensklingenberg.scrako.builder.getOrCreateList
import de.jensklingenberg.scrako.builder.getOrCreateVariable
import de.jensklingenberg.scrako.builder.scriptBuilder
import de.jensklingenberg.scrako.builder.spriteBuilder
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scrako.common.ScratchVariable
import de.jensklingenberg.scrako.model.Costume
import de.jensklingenberg.scratch.control.forever
import de.jensklingenberg.scratch.control.ifThen
import de.jensklingenberg.scratch.control.repeat
import de.jensklingenberg.scratch.control.wait
import de.jensklingenberg.scratch.data.changeVariableBy
import de.jensklingenberg.scratch.data.itemOfXList
import de.jensklingenberg.scratch.data.lengthOfList
import de.jensklingenberg.scratch.data.replaceItemOfWith
import de.jensklingenberg.scratch.data.setVariable
import de.jensklingenberg.scratch.data.showList
import de.jensklingenberg.scratch.event.Key
import de.jensklingenberg.scratch.event.sendBroadcast
import de.jensklingenberg.scratch.event.whenFlagClicked
import de.jensklingenberg.scratch.event.whenIReceiveBroadcast
import de.jensklingenberg.scratch.extension.pen.eraseAll
import de.jensklingenberg.scratch.extension.pen.stamp
import de.jensklingenberg.scratch.looks.hide
import de.jensklingenberg.scratch.motion.changeYby
import de.jensklingenberg.scratch.motion.move
import de.jensklingenberg.scratch.motion.setx
import de.jensklingenberg.scratch.motion.switchCostume
import de.jensklingenberg.scratch.operator.Add
import de.jensklingenberg.scratch.operator.Subtract
import de.jensklingenberg.scratch.operator.div
import de.jensklingenberg.scratch.operator.minus
import de.jensklingenberg.scratch.operator.plus
import de.jensklingenberg.scratch.operator.times
import de.jensklingenberg.scratch.procedures.call
import de.jensklingenberg.scratch.procedures.define
import de.jensklingenberg.scratch.sensing.keyIsPressed
import debugger.log
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
    dataFormat = "png",
    assetId = "0eabdcd3cdfeea01bb6ff8d3ad5fe301",
    md5ext = "0eabdcd3cdfeea01bb6ff8d3ad5fe301.png",
    rotationCenterX = 31.0,
    rotationCenterY = 26.0
)

const val PlayerIconID = "1"
const val BackgroundIconId = "2"
const val array_width = 12
const val DEBUG = false

fun ProjectBuilder.MySprite1() {

    val move = createBroadcast("move")
    val paint = createBroadcast("paint")
    val input = createBroadcast("input")

    val X_START = -230
    val Y_START = 120
    spriteBuilder("Sprite2") {
        addSprite(spriteArrow)
        addCostumes(listOf(costume1n, costume2n))

        val jens2 = getOrCreateList(
            "jens2",
            (0..84).map { "1" }
        )
        val playerIndex = getOrCreateVariable("playerIndex")
        val width = getOrCreateVariable("width")

        scriptBuilder {
            whenFlagClicked()
            if (DEBUG) {
                showList(jens2)
            }
            log(getIndexOf(0, 0))
            replaceItemOfWith(getIndexOf(0, 0), jens2, "2")
            setVariable(playerIndex, getIndexOf(0, 0))
            forever {

                sendBroadcast(paint)
                wait(0.2)
                sendBroadcast(input)
            }
        }

        scriptBuilder {
            whenIReceiveBroadcast(paint)
            call("paint1")
        }

        scriptBuilder {
            define("paint1", withoutRefresh = true) {
                val yy = getOrCreateVariable("paint_yy")
                val xx = getOrCreateVariable("paint_xx")
                hide()
                setVariable(width, array_width)
                setVariable(yy, 0)
                eraseAll()
                gotoxy(X_START, Y_START)
                //log(lengthOfList(jens2) / width)
                repeat(lengthOfList(jens2) / width) {
                    setx(X_START)
                    setVariable(xx, 0)
                    repeat(width - 1) {
                        switchCostume(itemOffArray(yy, xx, jens2))
                        move(40)
                        stamp()
                        changeVariableBy(xx, 1)
                    }
                    changeVariableBy(yy, 1)
                    changeYby(-40)
                    // gotoxy(-160, 120)
                    //stamp()
                }
            }
        }

        scriptBuilder {
            whenIReceiveBroadcast(input)
            ifThen(keyIsPressed(Key.RIGHT_ARROW)) {

                replaceItemOfWith(playerIndex, jens2, BackgroundIconId)
                replaceItemOfWith(playerIndex - 1, jens2, PlayerIconID)

                setVariable(playerIndex, playerIndex + 1)

            }
        }

    }
}

private fun itemOffArray(
    yy: ScratchVariable,
    xx: ScratchVariable,
    jens2: ScratchList
): ReporterBlock {

    return itemOfXList(yy times IntBlock(array_width) plus (xx plus 1), jens2)
}

private fun getIndexOf(
    yy: Int,
    xx: Int
): ReporterBlock {
    return IntBlock(yy) times IntBlock(array_width) plus (IntBlock(xx) plus 1)
}

private infix fun Int.minus(intBlock: IntBlock): ReporterBlock {
    return Subtract(IntBlock(this), intBlock)
}


infix operator fun Int.plus(int: ReporterBlock): ReporterBlock {
    return Add(IntBlock(this), int)
}
