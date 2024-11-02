package de.jensklingenberg.scratch3.control

import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.BooleanBlock
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.Block
import java.util.UUID

private class ControlWaitUntil(private val condition: BooleanBlock) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
        val uuid = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = "control_wait_until",
            inputs = mapOf(
                "CONDITION" to setValue(condition, uuid, context)
            )
        ).toBlock(nextUUID, parent)
        condition.visit(visitors, identifier, uuid, null, context)
    }
}

fun CommonScriptBuilder.waitUntil(block: BooleanBlock) = addNode(ControlWaitUntil(block))

