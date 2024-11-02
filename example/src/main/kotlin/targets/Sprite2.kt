package targets

import de.jensklingenberg.scrako.builder.ProjectBuilder
import de.jensklingenberg.scrako.builder.addCostumes
import de.jensklingenberg.scrako.builder.scriptBuilder
import de.jensklingenberg.scrako.builder.spriteBuilder
import de.jensklingenberg.scrako.common.Broadcast
import de.jensklingenberg.scrako.common.StringBlock
import de.jensklingenberg.scratch3.event.Key
import de.jensklingenberg.scratch3.event.whenFlagClicked
import de.jensklingenberg.scratch3.event.whenIReceiveBroadcast
import de.jensklingenberg.scratch3.event.whenKeyPress
import de.jensklingenberg.scratch3.looks.hide
import de.jensklingenberg.scratch3.looks.say
import de.jensklingenberg.scratch3.looks.show
import me.jens.Blockc
import me.jens.blockA
import me.jens.blockB
import de.jensklingenberg.scratch3.looks.think
import de.jensklingenberg.scratch3.looks.thinkforsecs

fun ProjectBuilder.Sprite2(paint: Broadcast) {
    spriteBuilder("Sprite2") {
        addPosition(100.0, 150.0)
        addCostumes(listOf(blockA, blockB, Blockc))
        scriptBuilder {
            whenIReceiveBroadcast(paint)
        }

        scriptBuilder {
            whenFlagClicked()
            //say("Hello!")
        }

        scriptBuilder {
            whenKeyPress(Key.A)
            show()
        }
    }
}