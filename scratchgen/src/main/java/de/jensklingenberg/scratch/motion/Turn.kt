package de.jensklingenberg.scratch.motion

import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.common.Block
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

