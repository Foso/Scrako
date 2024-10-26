package de.jensklingenberg.scratch.motion

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.common.setValue
import de.jensklingenberg.scratch.model.Block
import java.util.UUID

internal sealed class Turn(val opcode: String, private val reporterBlock: ReporterBlock) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        val operatorUUID = UUID.randomUUID()

        visitors[identifier.toString()] = BlockSpec(
            opcode = opcode,
            inputs = mapOf(
                "DEGREES" to setValue(reporterBlock, operatorUUID)
            )
        ).toBlock(nextUUID, parent)
        reporterBlock.visit(visitors, identifier.toString(), operatorUUID, null, context)
    }
}

