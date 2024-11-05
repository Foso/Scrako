package de.jensklingenberg.scratch3.looks

import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.model.Block

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

fun CommonScriptBuilder.goToFront() = addNode(GoTo("front"))
fun CommonScriptBuilder.goToBack() = addNode(GoTo("back"))