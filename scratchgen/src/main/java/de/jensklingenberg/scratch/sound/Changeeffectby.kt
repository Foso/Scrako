package de.jensklingenberg.scratch.sound

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.common.ScriptBuilder
import de.jensklingenberg.scratch.common.setValue
import de.jensklingenberg.scratch.model.Block
import java.util.UUID

private class Changeeffectby(val block0 : ReporterBlock, val effect: String) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        val block0Id = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.sound_changeeffectby,
            inputs = mapOf(
                "VALUE" to setValue(block0, block0Id) 
            ),
            fields = mapOf(
                "EFFECT" to listOf(effect,null)
            )
        ).toBlock(nextUUID, parent)
        block0.visit(visitors, identifier.toString(), block0Id, null, context)
    }
}

fun ScriptBuilder.changeEffectBy(block0: ReporterBlock, effect: String) = addChild(Changeeffectby(block0,effect))