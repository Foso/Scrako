package de.jensklingenberg.scratch.looks

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.model.Block
import java.util.UUID

private class GoTo(private val value: String) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.looks_gotofrontback,
            fields = mapOf("FRONT_BACK" to listOf(value, null))
        ).toBlock(nextUUID, parent, context.topLevel)
    }
}

fun NodeBuilder.GoToFront() = addChild(GoTo("front"))
fun NodeBuilder.GoToBack() = addChild(GoTo("back"))