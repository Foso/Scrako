package me.jens.targets

import de.jensklingenberg.scratch.event.whenFlagClicked
import de.jensklingenberg.scratch.looks.say
import de.jensklingenberg.scratch.motion.XPosition

import de.jensklingenberg.scrako.common.Target
import de.jensklingenberg.scrako.common.ProjectBuilder
import de.jensklingenberg.scrako.common.addSprite
import de.jensklingenberg.scrako.common.createTarget
import de.jensklingenberg.scrako.common.scriptBuilder
import de.jensklingenberg.scrako.common.targetBuilder
import me.jens.getGlobalVariable
import me.jens.spriteArrow

fun ProjectBuilder.createSprite2(): Target {
    val block = targetBuilder {
        addSprite(spriteArrow)
        scriptBuilder {
            val ee = getGlobalVariable("ddd")
            whenFlagClicked()
            say(XPosition)
        }
    }

    return createTarget(block.blocks, block.sprite!!, emptyList(), emptySet(), emptySet())

}