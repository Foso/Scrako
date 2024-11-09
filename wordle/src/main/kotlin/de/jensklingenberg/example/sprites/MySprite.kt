package de.jensklingenberg.example.sprites

import de.jensklingenberg.scrako.builder.ProjectBuilder
import de.jensklingenberg.scrako.builder.addCostumes
import de.jensklingenberg.scrako.builder.createBroadcast
import de.jensklingenberg.scrako.builder.createList
import de.jensklingenberg.scrako.builder.createVariable
import de.jensklingenberg.scrako.builder.scriptBuilder
import de.jensklingenberg.scrako.builder.spriteBuilder
import de.jensklingenberg.scrako.common.Broadcast
import de.jensklingenberg.scrako.common.StringBlock
import de.jensklingenberg.scrako.model.Costume2
import de.jensklingenberg.scratch3.control.repeat
import de.jensklingenberg.scratch3.data.changeVariableBy
import de.jensklingenberg.scratch3.data.itemOfXList
import de.jensklingenberg.scratch3.data.setVariable
import de.jensklingenberg.scratch3.event.sendBroadcast
import de.jensklingenberg.scratch3.event.whenFlagClicked
import de.jensklingenberg.scratch3.event.whenIReceiveBroadcast
import de.jensklingenberg.scratch3.extension.pen.stamp
import de.jensklingenberg.scratch3.motion.changeYby
import de.jensklingenberg.scratch3.motion.move
import de.jensklingenberg.scratch3.motion.setX
import de.jensklingenberg.scratch3.motion.switchCostume
import de.jensklingenberg.scratch3.operator.join
import de.jensklingenberg.scratch3.operator.lengthOf
import de.jensklingenberg.scratch3.operator.letterOf
import de.jensklingenberg.scratch3.procedures.call
import de.jensklingenberg.scratch3.procedures.define
import gotoxy

const val PlayerIconID = "2"
const val BackgroundIconId = "1"
const val array_width = 11
const val array_height = 8
const val DEBUG = true
const val X_START = -240
const val Y_START = 150
const val MOVE_DISTANCE = 40


val letterA = Costume2(
    name = "letterA",
    bitmapResolution = 1,
    dataFormat = "svg",
    rotationCenterX = 31,
    rotationCenterY = 26
)

val letterB = Costume2(
    name = "letterB",
    bitmapResolution = 1,
    dataFormat = "svg",
    rotationCenterX = 31,
    rotationCenterY = 26
)

val letterC = Costume2(
    name = "letterC",
    bitmapResolution = 1,
    dataFormat = "svg",
    rotationCenterX = 31,
    rotationCenterY = 26
)

val letterD = Costume2(
    name = "letterD",
    bitmapResolution = 1,
    dataFormat = "svg",
    rotationCenterX = 31,
    rotationCenterY = 26
)

val letterE = Costume2(
    name = "letterE",
    bitmapResolution = 1,
    dataFormat = "svg",
    rotationCenterX = 31,
    rotationCenterY = 26
)

val letterF = Costume2(
    name = "letterF",
    bitmapResolution = 1,
    dataFormat = "svg",
    rotationCenterX = 31,
    rotationCenterY = 26
)

val letterG = Costume2(
    name = "letterG",
    bitmapResolution = 1,
    dataFormat = "svg",
    rotationCenterX = 31,
    rotationCenterY = 26
)

val letterH = Costume2(
    name = "letterH",
    bitmapResolution = 1,
    dataFormat = "svg",
    rotationCenterX = 31,
    rotationCenterY = 26
)

val letterI = Costume2(
    name = "letterI",
    bitmapResolution = 1,
    dataFormat = "svg",
    rotationCenterX = 31,
    rotationCenterY = 26
)

val letterJ = Costume2(
    name = "letterJ",
    bitmapResolution = 1,
    dataFormat = "svg",
    rotationCenterX = 31,
    rotationCenterY = 26
)

val letterK = Costume2(
    name = "letterK",
    bitmapResolution = 1,
    dataFormat = "svg",
    rotationCenterX = 31,
    rotationCenterY = 26
)

val letterL = Costume2(
    name = "letterL",
    bitmapResolution = 1,
    dataFormat = "svg",
    rotationCenterX = 31,
    rotationCenterY = 26
)

val letterM = Costume2(
    name = "letterM",
    bitmapResolution = 1,
    dataFormat = "svg",
    rotationCenterX = 31,
    rotationCenterY = 26
)

val letterN = Costume2(
    name = "letterN",
    bitmapResolution = 1,
    dataFormat = "svg",
    rotationCenterX = 31,
    rotationCenterY = 26
)

val letterO = Costume2(
    name = "letterO",
    bitmapResolution = 1,
    dataFormat = "svg",
    rotationCenterX = 31,
    rotationCenterY = 26
)

val letterP = Costume2(
    name = "letterP",
    bitmapResolution = 1,
    dataFormat = "svg",
    rotationCenterX = 31,
    rotationCenterY = 26
)

val letterQ = Costume2(
    name = "letterQ",
    bitmapResolution = 1,
    dataFormat = "svg",
    rotationCenterX = 31,
    rotationCenterY = 26
)

val letterR = Costume2(
    name = "letterR",
    bitmapResolution = 1,
    dataFormat = "svg",
    rotationCenterX = 31,
    rotationCenterY = 26
)

val letterS = Costume2(
    name = "letterS",
    bitmapResolution = 1,
    dataFormat = "svg",
    rotationCenterX = 31,
    rotationCenterY = 26
)

val letterT = Costume2(
    name = "letterT",
    bitmapResolution = 1,
    dataFormat = "svg",
    rotationCenterX = 31,
    rotationCenterY = 26
)

val letterU = Costume2(
    name = "letterU",
    bitmapResolution = 1,
    dataFormat = "svg",
    rotationCenterX = 31,
    rotationCenterY = 26
)

val letterV = Costume2(
    name = "letterV",
    bitmapResolution = 1,
    dataFormat = "svg",
    rotationCenterX = 31,
    rotationCenterY = 26
)

val letterW = Costume2(
    name = "letterW",
    bitmapResolution = 1,
    dataFormat = "svg",
    rotationCenterX = 31,
    rotationCenterY = 26
)

val letterX = Costume2(
    name = "letterX",
    bitmapResolution = 1,
    dataFormat = "svg",
    rotationCenterX = 31,
    rotationCenterY = 26
)

val letterY = Costume2(
    name = "letterY",
    bitmapResolution = 1,
    dataFormat = "svg",
    rotationCenterX = 31,
    rotationCenterY = 26
)

val letterZ = Costume2(
    name = "letterZ",
    bitmapResolution = 1,
    dataFormat = "svg",
    rotationCenterX = 31,
    rotationCenterY = 26
)


fun ProjectBuilder.MySprite1(paint: Broadcast) {
    val X_START = -240
    val Y_START = 130
    val MOVE_DISTANCE = 40

    spriteBuilder("Sprite123") {
        val broadcast = createBroadcast("paint")
        addCostumes(
            listOf(
                letterA,
                letterB,
                letterC,
                letterD,
                letterE,
                letterF,
                letterG,
                letterH,
                letterI,
                letterJ,
                letterK,
                letterL,
                letterM,
                letterN,
                letterO,
                letterP,
                letterQ,
                letterR,
                letterS,
                letterT,
                letterU,
                letterV,
                letterW,
                letterX,
                letterY,
                letterZ
            )
        )
        val insertWords =
            createList("INSERTWORDS", listOf("HOUND", "BOUND", "ROUND", "SOUND", "FOUND", "POUND", "MOUND"))
        val index = createVariable("index")
        /**
         * Start
         */
        scriptBuilder {
            whenFlagClicked()
            // ask("What is the word")
            //   addToList(insertWords, Answer)
            sendBroadcast(broadcast)
        }

        scriptBuilder {
            whenIReceiveBroadcast(broadcast)
            call("paint")
        }

        scriptBuilder {
            define("paint", withoutRefresh = true) {
                val currentIndex = createVariable("currentIndex")
                val innerIndex = createVariable("INNERINDEX")

                setVariable(currentIndex, 1)
                gotoxy(X_START, Y_START)
                repeat(lengthOf(insertWords)) {
                    setVariable(innerIndex, 1)
                    setX(X_START)
                    val myWord = itemOfXList(currentIndex, insertWords)
                    repeat(lengthOf(myWord)) {
                        switchCostume(join(StringBlock("letter"), letterOf(innerIndex, myWord)))
                        move(40)
                        stamp()
                        changeVariableBy(innerIndex, 1)
                    }

                    changeYby(-MOVE_DISTANCE)

                    changeVariableBy(currentIndex, 1)
                }

            }
        }
    }


}
