package de.jensklingenberg.scratch.extension.pen

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.ScriptBuilder
import de.jensklingenberg.scratch.model.Block
import java.util.UUID

private class EraseAll : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        visitors[identifier.toString()] = BlockSpec(
            opcode = PenOpCode.pen_clear,
        ).toBlock(nextUUID, parent)
    }
}

fun ScriptBuilder.eraseAll() = addChild(EraseAll())