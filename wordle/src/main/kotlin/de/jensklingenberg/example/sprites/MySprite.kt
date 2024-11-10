package de.jensklingenberg.example.sprites

import de.jensklingenberg.de.jensklingenberg.example.sprites.letterA
import de.jensklingenberg.de.jensklingenberg.example.sprites.letterB
import de.jensklingenberg.de.jensklingenberg.example.sprites.letterC
import de.jensklingenberg.de.jensklingenberg.example.sprites.letterD
import de.jensklingenberg.de.jensklingenberg.example.sprites.letterDBlue
import de.jensklingenberg.de.jensklingenberg.example.sprites.letterDGreen
import de.jensklingenberg.de.jensklingenberg.example.sprites.letterE
import de.jensklingenberg.de.jensklingenberg.example.sprites.letterF
import de.jensklingenberg.de.jensklingenberg.example.sprites.letterG
import de.jensklingenberg.de.jensklingenberg.example.sprites.letterH
import de.jensklingenberg.de.jensklingenberg.example.sprites.letterHBlue
import de.jensklingenberg.de.jensklingenberg.example.sprites.letterHGreen
import de.jensklingenberg.de.jensklingenberg.example.sprites.letterI
import de.jensklingenberg.de.jensklingenberg.example.sprites.letterJ
import de.jensklingenberg.de.jensklingenberg.example.sprites.letterK
import de.jensklingenberg.de.jensklingenberg.example.sprites.letterL
import de.jensklingenberg.de.jensklingenberg.example.sprites.letterM
import de.jensklingenberg.de.jensklingenberg.example.sprites.letterN
import de.jensklingenberg.de.jensklingenberg.example.sprites.letterNBlue
import de.jensklingenberg.de.jensklingenberg.example.sprites.letterNGreen
import de.jensklingenberg.de.jensklingenberg.example.sprites.letterO
import de.jensklingenberg.de.jensklingenberg.example.sprites.letterOBlue
import de.jensklingenberg.de.jensklingenberg.example.sprites.letterOGreen
import de.jensklingenberg.de.jensklingenberg.example.sprites.letterP
import de.jensklingenberg.de.jensklingenberg.example.sprites.letterQ
import de.jensklingenberg.de.jensklingenberg.example.sprites.letterR
import de.jensklingenberg.de.jensklingenberg.example.sprites.letterS
import de.jensklingenberg.de.jensklingenberg.example.sprites.letterT
import de.jensklingenberg.de.jensklingenberg.example.sprites.letterU
import de.jensklingenberg.de.jensklingenberg.example.sprites.letterUBlue
import de.jensklingenberg.de.jensklingenberg.example.sprites.letterUGreen
import de.jensklingenberg.de.jensklingenberg.example.sprites.letterV
import de.jensklingenberg.de.jensklingenberg.example.sprites.letterW
import de.jensklingenberg.de.jensklingenberg.example.sprites.letterX
import de.jensklingenberg.de.jensklingenberg.example.sprites.letterY
import de.jensklingenberg.de.jensklingenberg.example.sprites.letterZ
import de.jensklingenberg.scrako.builder.ProjectBuilder
import de.jensklingenberg.scrako.builder.addCostumes
import de.jensklingenberg.scrako.builder.createVariable
import de.jensklingenberg.scrako.builder.scriptBuilder
import de.jensklingenberg.scrako.builder.spriteBuilder
import de.jensklingenberg.scrako.common.Broadcast
import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scrako.common.ScratchVariable
import de.jensklingenberg.scrako.common.StringBlock
import de.jensklingenberg.scratch3.control.ifElse
import de.jensklingenberg.scratch3.control.repeat
import de.jensklingenberg.scratch3.data.changeVariableBy
import de.jensklingenberg.scratch3.data.itemOfXList
import de.jensklingenberg.scratch3.data.setVariable
import de.jensklingenberg.scratch3.event.whenFlagClicked
import de.jensklingenberg.scratch3.event.whenIReceiveBroadcast
import de.jensklingenberg.scratch3.extension.pen.stamp
import de.jensklingenberg.scratch3.looks.hide
import de.jensklingenberg.scratch3.looks.show
import de.jensklingenberg.scratch3.motion.changeYby
import de.jensklingenberg.scratch3.motion.move
import de.jensklingenberg.scratch3.motion.setX
import de.jensklingenberg.scratch3.motion.switchCostume
import de.jensklingenberg.scratch3.operator.contains
import de.jensklingenberg.scratch3.operator.eq
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




fun ProjectBuilder.MySprite1(broadcast: Broadcast, searchWord: ScratchVariable, insertWords: ScratchList) {
    val X_START = -240
    val Y_START = 130
    val MOVE_DISTANCE = 40

    spriteBuilder("Sprite123") {

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
                letterZ,
                letterHBlue,
                letterOBlue,
                letterUBlue,
                letterNBlue,
                letterDBlue,
                letterHGreen,
                letterOGreen,
                letterUGreen,
                letterNGreen,
                letterDGreen
            )
        )

        scriptBuilder {
            whenFlagClicked()
            hide()
        }

        scriptBuilder {
            whenIReceiveBroadcast(broadcast)
            call("paint")
        }

        scriptBuilder {
            define("paint", withoutRefresh = true) {

                val currentIndex = createVariable("currentIndex")
                val innerIndex = createVariable("INNERINDEX")
                show()
                setVariable(currentIndex, 1)
                gotoxy(X_START, Y_START)
                repeat(lengthOf(insertWords)) {
                    setVariable(innerIndex, 1)
                    setX(X_START)
                    val guessedWord = itemOfXList(currentIndex, insertWords)
                    repeat(lengthOf(guessedWord)) {

                        val letterOfGuessedWord = letterOf(innerIndex, guessedWord)
                        val letterOfSearchedWord = letterOf(innerIndex, searchWord)

                        val block = join(StringBlock("letter"), letterOfGuessedWord)

                        ifElse(letterOfSearchedWord eq letterOfGuessedWord, leftStack = {
                            switchCostume(join(block, StringBlock("Green")))
                        }, rightStack = {
                            ifElse(contains(searchWord, letterOfGuessedWord), leftStack = {
                                switchCostume(join(block, StringBlock("Blue")))
                            }, rightStack = {
                                switchCostume(block)
                            })
                        })
                        // log(join(block, StringBlock("Blue")))

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
