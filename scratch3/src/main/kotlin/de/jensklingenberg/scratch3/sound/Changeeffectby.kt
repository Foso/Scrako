package de.jensklingenberg.scratch3.sound

import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scratch3.common.OpCode
import java.util.UUID

private class Changeeffectby(val block0: ReporterBlock, val effect: String) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context,
    ) {
        val block0Id = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = OpCode.sound_changeeffectby,
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

fun CommonScriptBuilder.changeEffectBy(effect: SoundEffect, block0: ReporterBlock) =
    addNode(Changeeffectby(block0, effect.name.lowercase()))