package de.jensklingenberg.scratch.looks

import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.common.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.Node

private class GoTo(private val value: String) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context,

        ) {
        visitors[identifier] = BlockSpec(
            opcode = "looks_gotofrontback",
            fields = mapOf("FRONT_BACK" to listOf(value, null))
        ).toBlock(nextUUID, parent)
    }
}

fun ScriptBuilder.goToFront() = addNode(GoTo("front"))
fun ScriptBuilder.goToBack() = addNode(GoTo("back"))