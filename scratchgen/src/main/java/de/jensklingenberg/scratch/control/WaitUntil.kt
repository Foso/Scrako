package de.jensklingenberg.scratch.control

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.ScriptBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.setValue
import de.jensklingenberg.scratch.model.Block
import de.jensklingenberg.scratch.operator.BooleanBlock
import java.util.UUID

private class ControlWaitUntil(private val condition: BooleanBlock) : Node {
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
                "CONDITION" to setValue(condition, uuid)
            )
        ).toBlock(nextUUID, parent)
        condition.visit(visitors, identifier.toString(), uuid, null, context.copy(topLevel = false))
    }
}

fun ScriptBuilder.waitUntil(block: BooleanBlock) = addChild(ControlWaitUntil(block))

