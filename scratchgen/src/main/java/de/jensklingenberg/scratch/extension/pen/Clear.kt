package de.jensklingenberg.scratch.extension.pen

import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ScriptBuilder
import de.jensklingenberg.scrako.common.Block
import java.util.UUID

private class EraseAll : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        
    ) {
        visitors[identifier.toString()] = BlockSpec(
            opcode = PenOpCode.pen_clear,
        ).toBlock(nextUUID, parent)
    }
}

fun ScriptBuilder.eraseAll() = addChild(EraseAll())