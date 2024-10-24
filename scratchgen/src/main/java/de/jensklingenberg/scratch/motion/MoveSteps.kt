package de.jensklingenberg.scratch.motion

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.DoubleBlock
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.common.setValue
import de.jensklingenberg.scratch.model.Block
import java.util.UUID

private class Move(val block: ReporterBlock) : Node {

    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        val childId = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.motion_movesteps,
            inputs = mapOf("STEPS" to setValue(block, childId))
        ).toBlock(nextUUID, parent, context.topLevel)
        block.visit(visitors, identifier.toString(), childId, null, context)

    }
}

fun NodeBuilder.move(steps: Double) = addChild(Move(DoubleBlock(steps)))
fun NodeBuilder.move(steps: ReporterBlock) = addChild(Move(steps))


