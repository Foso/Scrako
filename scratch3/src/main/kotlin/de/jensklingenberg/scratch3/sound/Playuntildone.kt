package de.jensklingenberg.scratch3.sound

import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.BlockFull
import de.jensklingenberg.scrako.model.Sound
import java.util.UUID

private class Playuntildone(val block0: ReporterBlock) : Node {
    override fun visit(
        visitors: MutableMap<String, BlockFull>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
        val block0Id = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = "sound_playuntildone",
            inputs = mapOf(
                "SOUND_MENU" to setValue(block0, block0Id, context)
            )
        ).toBlock(nextUUID, parent)
        block0.visit(visitors, identifier, block0Id, null, context)
    }
}

fun CommonScriptBuilder.playSoundUntilDone(s: Sound) = addNode(Playuntildone(SoundsMenu(s.name)))
