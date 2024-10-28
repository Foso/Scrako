package de.jensklingenberg.scratch.looks

import de.jensklingenberg.scrako.common.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scratch.common.OpCode
import java.util.UUID

private class GoTo(private val value: String) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context,

        ) {
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.looks_gotofrontback,
            fields = mapOf("FRONT_BACK" to listOf(value, null))
        ).toBlock(nextUUID, parent)
    }
}

fun ScriptBuilder.GoToFront() = addChild(GoTo("front"))
fun ScriptBuilder.GoToBack() = addChild(GoTo("back"))