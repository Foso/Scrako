package de.jensklingenberg.scratch.extension.pen

import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.Node

private class EraseAll : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context,

        ) {
        visitors[identifier] = BlockSpec(
            opcode = PenOpCode.pen_clear,
        ).toBlock(nextUUID, parent)
    }
}

fun ScriptBuilder.eraseAll() = addNode(EraseAll())