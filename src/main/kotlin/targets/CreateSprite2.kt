package me.jens.targets

import de.jensklingenberg.scratch.addSprite
import de.jensklingenberg.scratch.blockBuilder
import de.jensklingenberg.scratch.common.createBlocks23
import de.jensklingenberg.scratch.event.whenFlagClicked
import de.jensklingenberg.scratch.looks.say
import de.jensklingenberg.scratch.model.Target
import de.jensklingenberg.scratch.model.createTarget
import de.jensklingenberg.scratch.motion.XPosition
import de.jensklingenberg.scratch.targetBuilder
import me.jens.spriteArrow

fun createSprite2(): Target {
    val block = targetBuilder {
        addSprite(spriteArrow)
        blockBuilder {
            whenFlagClicked()
            say(XPosition)
        }
    }

    return createTarget(block.blocks, block.sprite!!, emptyList(), emptySet(), emptySet())

}