package de.jensklingenberg.scratch.sound

import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.common.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scratch.common.OpCode
import java.util.UUID

private class Setvolumeto(val block0: ReporterBlock) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context,

        ) {
        val block0Id = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.sound_setvolumeto,
            inputs = mapOf(
                "VOLUME" to setValue(block0, block0Id, context)
            ),
            fields = mapOf(

            )
        ).toBlock(nextUUID, parent)
        block0.visit(visitors, identifier.toString(), block0Id, null, context)
    }
}

fun ScriptBuilder.setVolumeTo(block0: ReporterBlock) = addNode(Setvolumeto(block0))