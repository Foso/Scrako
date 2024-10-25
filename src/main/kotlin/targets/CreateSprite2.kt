package me.jens.targets

import de.jensklingenberg.scratch.blockBuilder
import de.jensklingenberg.scratch.common.createBlocks23
import de.jensklingenberg.scratch.event.whenFlagClicked
import de.jensklingenberg.scratch.looks.say
import de.jensklingenberg.scratch.model.Target
import de.jensklingenberg.scratch.model.createTarget
import de.jensklingenberg.scratch.motion.XPosition
import me.jens.spriteArrow

fun createSprite2(): Target {
    val block = blockBuilder {
        whenFlagClicked()
        say(XPosition)
    }
    val blocko = createBlocks23(listOf(block.childs))

    return createTarget(blocko, spriteArrow, emptyList(), emptySet(), emptySet())

}