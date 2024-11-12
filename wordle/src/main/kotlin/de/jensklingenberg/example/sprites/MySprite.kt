package de.jensklingenberg.example.sprites

import de.jensklingenberg.de.jensklingenberg.example.sprites.letterA
import de.jensklingenberg.de.jensklingenberg.example.sprites.letterABlue
import de.jensklingenberg.de.jensklingenberg.example.sprites.letterAGreen
import de.jensklingenberg.de.jensklingenberg.example.sprites.letterB
import de.jensklingenberg.de.jensklingenberg.example.sprites.letterBGreen
import de.jensklingenberg.de.jensklingenberg.example.sprites.letterC
import de.jensklingenberg.de.jensklingenberg.example.sprites.letterD
import de.jensklingenberg.de.jensklingenberg.example.sprites.letterDBlue
import de.jensklingenberg.de.jensklingenberg.example.sprites.letterDGreen
import de.jensklingenberg.de.jensklingenberg.example.sprites.letterE
import de.jensklingenberg.de.jensklingenberg.example.sprites.letterEGreen
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
import de.jensklingenberg.scrako.builder.Config
import de.jensklingenberg.scrako.builder.ProjectBuilder
import de.jensklingenberg.scrako.builder.createVariable
import de.jensklingenberg.scrako.builder.scriptBuilder
import de.jensklingenberg.scrako.builder.spriteBuilder
import de.jensklingenberg.scrako.common.Broadcast
import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scrako.common.ScratchVariable
import de.jensklingenberg.scrako.common.StringBlock
import de.jensklingenberg.scratch3.common.meow
import de.jensklingenberg.scratch3.control.ifElse
import de.jensklingenberg.scratch3.control.repeat
import de.jensklingenberg.scratch3.data.changeVariableBy
import de.jensklingenberg.scratch3.data.item
import de.jensklingenberg.scratch3.data.length
import de.jensklingenberg.scratch3.data.setVariable
import de.jensklingenberg.scratch3.event.whenFlagClicked
import de.jensklingenberg.scratch3.event.whenIReceiveBroadcast
import de.jensklingenberg.scratch3.extension.pen.eraseAll
import de.jensklingenberg.scratch3.extension.pen.stamp
import de.jensklingenberg.scratch3.looks.hide
import de.jensklingenberg.scratch3.looks.say
import de.jensklingenberg.scratch3.looks.show
import de.jensklingenberg.scratch3.looks.switchCostume
import de.jensklingenberg.scratch3.motion.changeYby
import de.jensklingenberg.scratch3.motion.move
import de.jensklingenberg.scratch3.motion.setX
import de.jensklingenberg.scratch3.operator.contains
import de.jensklingenberg.scratch3.operator.eq
import de.jensklingenberg.scratch3.operator.join
import de.jensklingenberg.scratch3.operator.length
import de.jensklingenberg.scratch3.operator.lengthOf
import de.jensklingenberg.scratch3.operator.letter
import de.jensklingenberg.scratch3.procedures.call
import de.jensklingenberg.scratch3.procedures.define
import debugger.breakpoint
import debugger.log
import goToxy


fun ProjectBuilder.MySprite1(
    broadcast: Broadcast,
    secretWord: ScratchVariable,
    insertWords: ScratchList,
    sayAnswerBroadcast: Broadcast
) {
    val X_START = -230
    val Y_START = 130
    val MOVE_DISTANCE = 40

    spriteBuilder("Sprite123") {
        val rowIndex = createVariable("rowIndex")
        val columnIndex = createVariable("columnIndex")

        config = Config(
           // posX = X_START.toDouble(),
            costumes = listOf(
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
                letterABlue,
                letterHBlue,
                letterOBlue,
                letterUBlue,
                letterNBlue,
                letterDBlue,
                letterAGreen,
                letterBGreen,
                letterEGreen,
                letterHGreen,
                letterOGreen,
                letterUGreen,
                letterNGreen,
                letterDGreen
            )
        )

        addSounds(listOf(meow))


        scriptBuilder {
            whenFlagClicked()
            hide()
        }

        scriptBuilder {
            whenIReceiveBroadcast(sayAnswerBroadcast)
            show()
            say(secretWord)
        }

        scriptBuilder {
            whenIReceiveBroadcast(broadcast)
            call("paint")
        }

        scriptBuilder {

            define("paint2", withoutRefresh = true) {
                show()
                eraseAll()
                goToxy(X_START, Y_START)
                repeat(insertWords.length()){
                    stamp()
                    //changeXby(MOVE_DISTANCE)
                    changeYby(-MOVE_DISTANCE)
                }
                hide()
            }
        }

        scriptBuilder {
            //return@scriptBuilder
            define("paint", withoutRefresh = true) {
                show()
                log("Paint was called")
                log(insertWords.length())
                setVariable(rowIndex, 1)
                goToxy(X_START, Y_START)
                repeat(lengthOf(insertWords)) {
                    setVariable(columnIndex, 1)
                    setX(X_START)
                    val guessedWord = insertWords.item(rowIndex)//itemOfXList(rowIndex, insertWords)
                    repeat(guessedWord.length()) {

                        val letterOfGuessedWord = guessedWord.letter(columnIndex)//letterOf(innerIndex, guessedWord)
                        val letterOfSearchedWord = secretWord.letter(columnIndex)//letterOf(columnIndex, searchWord)

                        val block = join(StringBlock("letter"), letterOfGuessedWord)

                        ifElse(letterOfSearchedWord eq letterOfGuessedWord, leftStack = {
                            log("Letter is equal")
                            log(letterOfGuessedWord)
                            switchCostume(join(block, StringBlock("Green")))
                        }, rightStack = {
                            ifElse(contains(secretWord, letterOfGuessedWord), leftStack = {
                                log("Letter is in word")
                                log(letterOfGuessedWord)
                                switchCostume(join(block, StringBlock("Blue")))
                            }, rightStack = {
                                log("Letter is not in word")
                                log(letterOfGuessedWord)
                                switchCostume(block)
                            })
                        })

                        move(MOVE_DISTANCE)
                        breakpoint()
                        stamp()
                        changeVariableBy(columnIndex, 1)
                    }

                    changeYby(-MOVE_DISTANCE)
                    changeVariableBy(rowIndex, 1)
                }
                hide()
            }
        }
    }
}

