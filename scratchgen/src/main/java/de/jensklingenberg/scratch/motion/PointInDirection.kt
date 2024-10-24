package de.jensklingenberg.scratch.motion

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.IntBlock
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.common.setValue
import de.jensklingenberg.scratch.model.Block
import java.util.UUID

private class PointInDirection(val block: ReporterBlock) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        val childId = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.motion_pointindirection,
            inputs = mapOf("DIRECTION" to setValue(block, childId))
        ).toBlock(nextUUID, parent, context.topLevel)
        block.visit(visitors, identifier.toString(), childId, null, context)

    }
}

fun NodeBuilder.pointInDirection(block: ReporterBlock) = addChild(PointInDirection(block))
fun NodeBuilder.pointInDirection(degrees: Int) = addChild(PointInDirection(IntBlock(degrees)))