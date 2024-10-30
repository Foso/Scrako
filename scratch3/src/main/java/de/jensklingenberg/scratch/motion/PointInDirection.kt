package de.jensklingenberg.scratch.motion

import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.MotionBlock
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scratch.common.OpCode
import java.util.UUID

private class PointInDirection(val block: ReporterBlock) : Node, MotionBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context,

        ) {
        val childId = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = OpCode.motion_pointindirection,
            inputs = mapOf("DIRECTION" to setValue(block, childId, context))
        ).toBlock(nextUUID, parent)
        block.visit(visitors, identifier, childId, null, context)

    }
}

fun ScriptBuilder.pointInDirection(block: ReporterBlock) = addNode(PointInDirection(block))
fun ScriptBuilder.pointInDirection(degrees: Int) = addNode(PointInDirection(IntBlock(degrees)))