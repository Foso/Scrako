package de.jensklingenberg.example.sprites

import de.jensklingenberg.scrako.builder.ProjectBuilder
import de.jensklingenberg.scrako.builder.addCostumes
import de.jensklingenberg.scrako.builder.scriptBuilder
import de.jensklingenberg.scrako.builder.spriteBuilder
import de.jensklingenberg.scrako.common.Broadcast
import de.jensklingenberg.scratch3.event.Key
import de.jensklingenberg.scratch3.event.whenFlagClicked
import de.jensklingenberg.scratch3.event.whenIReceiveBroadcast
import de.jensklingenberg.scratch3.event.whenKeyPress
import de.jensklingenberg.scratch3.looks.show
import de.jensklingenberg.example.costumes.Blockc
import de.jensklingenberg.example.costumes.blockA
import de.jensklingenberg.example.costumes.blockB
import de.jensklingenberg.example.ext.case
import de.jensklingenberg.example.ext.switch
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.StringBlock
import de.jensklingenberg.scratch3.looks.hide
import de.jensklingenberg.scratch3.looks.say
import de.jensklingenberg.scratch3.sensing.Answer

fun ProjectBuilder.Sprite2(paint: Broadcast) {
    spriteBuilder("Sprite2") {
        addPosition(100.0, 150.0)
        addCostumes(listOf(blockA, blockB, Blockc))
        scriptBuilder {
            whenIReceiveBroadcast(paint)
        }

        scriptBuilder {
            whenFlagClicked()
            hide()
        }

        scriptBuilder {
            whenKeyPress(Key.A)
            show()

            switch(Answer){
                case(1){
                    say("1")
                }

                case("23"){
                    say("23")
                }
            }
        }
    }
}