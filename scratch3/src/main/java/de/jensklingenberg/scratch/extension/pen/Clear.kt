package de.jensklingenberg.scratch.extension.pen

import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.common.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.Node
import java.util.UUID

private class EraseAll : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context,

        ) {
        visitors[identifier.toString()] = BlockSpec(
            opcode = PenOpCode.pen_clear,
        ).toBlock(nextUUID, parent)
    }
}

fun ScriptBuilder.eraseAll() = addNode(EraseAll())