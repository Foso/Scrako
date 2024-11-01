package targets

import de.jensklingenberg.scrako.builder.ProjectBuilder
import de.jensklingenberg.scrako.builder.addCostumes
import de.jensklingenberg.scrako.builder.scriptBuilder
import de.jensklingenberg.scrako.builder.spriteBuilder
import de.jensklingenberg.scrako.common.Broadcast
import de.jensklingenberg.scrako.common.StringBlock
import de.jensklingenberg.scratch.event.Key
import de.jensklingenberg.scratch.event.whenFlagClicked
import de.jensklingenberg.scratch.event.whenIReceiveBroadcast
import de.jensklingenberg.scratch.event.whenKeyPress
import de.jensklingenberg.scratch.looks.hide
import de.jensklingenberg.scratch.looks.show
import me.jens.Blockc
import me.jens.blockA
import me.jens.blockB
import de.jensklingenberg.scratch.looks.think
import de.jensklingenberg.scratch.looks.thinkforsecs

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
            think(StringBlock("Hello"))
            thinkforsecs(StringBlock("Hello"), StringBlock("2"))
        }

        scriptBuilder {
            whenKeyPress(Key.A)
            show()
        }
    }
}