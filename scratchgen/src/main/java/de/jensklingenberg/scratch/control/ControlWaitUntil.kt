package de.jensklingenberg.scratch.control

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.setValue
import de.jensklingenberg.scratch.model.Block
import de.jensklingenberg.scratch.operator.BooleanBlock
import java.util.UUID

class ControlWaitUntil(private val block: BooleanBlock) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        val uuid = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.control_wait_until,
            inputs = mapOf(
                "CONDITION" to setValue(block, uuid)
            )
        ).toBlock(nextUUID, parent, context.topLevel)
        block.visit(visitors, identifier.toString(), uuid, null, context.copy(topLevel = false))
    }
}

fun NodeBuilder.waitUntil(block: BooleanBlock) = addChild(ControlWaitUntil(block))
