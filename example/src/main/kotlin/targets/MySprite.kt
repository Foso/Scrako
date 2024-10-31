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
import de.jensklingenberg.scrako.common.BooleanBlock
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scrako.common.ScratchVariable
import de.jensklingenberg.scrako.common.StringBlock
import de.jensklingenberg.scrako.model.Costume
import de.jensklingenberg.scratch.control.repeat
import de.jensklingenberg.scratch.data.changeVariableBy
import de.jensklingenberg.scratch.data.itemOfXList
import de.jensklingenberg.scratch.data.lengthOfList
import de.jensklingenberg.scratch.data.replaceItemOfWith
import de.jensklingenberg.scratch.data.setVariable
import de.jensklingenberg.scratch.event.sendBroadcast
import de.jensklingenberg.scratch.event.whenFlagClicked
import de.jensklingenberg.scratch.event.whenIReceiveBroadcast
import de.jensklingenberg.scratch.extension.pen.eraseAll
import de.jensklingenberg.scratch.extension.pen.stamp
import de.jensklingenberg.scratch.looks.hide
import de.jensklingenberg.scratch.looks.say
import de.jensklingenberg.scratch.motion.changeYby
import de.jensklingenberg.scratch.motion.move
import de.jensklingenberg.scratch.motion.setx
import de.jensklingenberg.scratch.motion.switchCostume
import de.jensklingenberg.scratch.operator.Add
import de.jensklingenberg.scratch.operator.Subtract
import de.jensklingenberg.scratch.operator.div
import de.jensklingenberg.scratch.operator.times
import de.jensklingenberg.scratch.procedures.call
import de.jensklingenberg.scratch.procedures.define
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

const val PlayerIconID = 1
const val BackgroundIconId = 2
const val array_width = 6

fun ProjectBuilder.MyTarget() {

    val move = createBroadcast("move")
    val paint = createBroadcast("paint")
    val input = createBroadcast("input")

    val X_START = -160
    val Y_START = 120
    spriteBuilder("Sprite2") {
        addSprite(spriteArrow)
        addCostumes(listOf(costume1n, costume2n))
        val gridY = getOrCreateVariable("gridY")
        val gridX = getOrCreateVariable("gridX")
        val jens2 = getOrCreateList(
            "jens2",
            listOf(
                "1",
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
                "1"
            )
        )
        val playerIndex = getOrCreateVariable("playerIndex")
        val width = getOrCreateVariable("width")
        val listIndex = getOrCreateVariable("listIndex")

        scriptBuilder {
            whenFlagClicked()
            sendBroadcast(paint)
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
                    setVariable(xx,0)
                    repeat(width-1) {
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

    }
}

private fun itemOffArray(
    yy: ScratchVariable,
    xx: ScratchVariable,
    jens2: ScratchList
): ReporterBlock {
    return itemOfXList((yy times IntBlock(array_width)) plus xx, jens2)
}


private infix operator fun ReporterBlock.plus(i: Int): Add {
    return Add(this, IntBlock(i))
}

private infix operator fun ReporterBlock.plus(i: ReporterBlock): Add {
    return Add(this, i)
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
