package de.jensklingenberg.example.sprites

import de.jensklingenberg.scrako.builder.ProjectBuilder
import de.jensklingenberg.scrako.builder.addCostumes
import de.jensklingenberg.scrako.builder.scriptBuilder
import de.jensklingenberg.scrako.builder.spriteBuilder
import de.jensklingenberg.scrako.common.Broadcast
import de.jensklingenberg.scratch3.event.Key
import de.jensklingenberg.scratch3.event.whenFlagClicked
import de.jensklingenberg.scratch3.event.whenKeyPress
import de.jensklingenberg.scratch3.looks.show
import de.jensklingenberg.example.costumes.Blockc
import de.jensklingenberg.example.costumes.blockA
import de.jensklingenberg.example.costumes.blockB
import de.jensklingenberg.scrako.builder.createVariable
import de.jensklingenberg.scrako.common.BooleanBlock
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scratch3.control.ifThen
import de.jensklingenberg.scratch3.control.repeat
import de.jensklingenberg.scratch3.looks.hide
import de.jensklingenberg.scratch3.looks.say
import de.jensklingenberg.scratch3.operator.GreaterThan
import de.jensklingenberg.scratch3.operator.OperatorEquals
import de.jensklingenberg.scratch3.operator.plus

fun ProjectBuilder.Sprite2(paint: Broadcast) {
    spriteBuilder("Sprite2") {
        addPosition(100.0, 150.0)
        addCostumes(listOf(blockA, blockB, Blockc))
        val test = createVariable("test")

        scriptBuilder {
            whenFlagClicked()
            hide()
            repeat(test plus 1){
                say("Hello")
            }
        }

        scriptBuilder {
            whenKeyPress(Key.A)
            show()
            ifThen(1 eq 1){
                say("Hello")
            }

        }
    }
}

private infix fun Int.gt(i: Int): BooleanBlock {
    return GreaterThan(IntBlock(this), IntBlock(i))
}

private infix fun Int.eq(i: Int): BooleanBlock {
    return OperatorEquals(IntBlock(this), IntBlock(i))
}
