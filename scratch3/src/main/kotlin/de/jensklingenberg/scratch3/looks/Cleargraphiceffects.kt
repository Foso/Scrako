package de.jensklingenberg.scratch3.looks

import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.model.BlockFull

private class Cleargraphiceffects() : Node {
    override fun visit(
        visitors: MutableMap<String, BlockFull>,
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

fun CommonScriptBuilder.cleargraphiceffects() = addNode(Cleargraphiceffects())