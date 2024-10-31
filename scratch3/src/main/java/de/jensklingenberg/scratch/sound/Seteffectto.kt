package de.jensklingenberg.scratch.sound

import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.Block
import java.util.UUID

enum class SoundEffect(val effect: String) {
    PITCH("pitch"),
    PAN("pan"),
    RATE("rate"),
    VOLUME("volume")
}

private class Seteffectto(val block0: ReporterBlock, val effect: String) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
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

fun ScriptBuilder.setEffectTo(effect: SoundEffect, block0: ReporterBlock) =
    addNode(Seteffectto(block0, effect.name.lowercase()))