package de.jensklingenberg.scratch.looks

import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.model.Block

private class Cleargraphiceffects() : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {

        visitors[identifier] = BlockSpec(
            opcode = "looks_cleargraphiceffects",
        ).toBlock(nextUUID, parent)

    }
}

fun ScriptBuilder.cleargraphiceffects() = addNode(Cleargraphiceffects())