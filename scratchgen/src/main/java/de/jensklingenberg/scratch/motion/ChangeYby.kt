package de.jensklingenberg.scratch.motion

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.IntBlock
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.common.setValue
import de.jensklingenberg.scratch.model.Block
import java.util.UUID

private class ChangeYby(val block: ReporterBlock) : ReporterBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        val operatorUUID = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.motion_changeyby,
            inputs = mapOf(
                "DY" to setValue(block, operatorUUID)
            )
        ).toBlock(nextUUID, parent, context.topLevel)
        block.visit(visitors, identifier.toString(), operatorUUID, null, context)
    }
}

fun NodeBuilder.changeYby(value: Int) = addChild(ChangeYby(IntBlock(value)))