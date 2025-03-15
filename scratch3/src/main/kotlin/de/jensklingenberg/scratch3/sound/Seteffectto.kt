package de.jensklingenberg.scratch3.sound

import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.BlockFull
import java.util.UUID

enum class SoundEffect(val effect: String) {
    PITCH("pitch"),
    PAN("pan"),
    RATE("rate"),
    VOLUME("volume")
}

private class Seteffectto(val block0: ReporterBlock, val effect: String) : Node {
    override fun visit(
        visitors: MutableMap<String, BlockFull>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context,

        ) {
        val block0Id = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = "sound_seteffectto",
            inputs = mapOf(
                "VALUE" to setValue(block0, block0Id, context)
            ),
            fields = mapOf(
                "EFFECT" to listOf(effect, null)
            )
        ).toBlock(nextUUID, parent)
        block0.visit(visitors, identifier, block0Id, null, context)
    }
}

fun CommonScriptBuilder.setEffectTo(effect: SoundEffect, block0: ReporterBlock) =
    addNode(Seteffectto(block0, effect.name.lowercase()))