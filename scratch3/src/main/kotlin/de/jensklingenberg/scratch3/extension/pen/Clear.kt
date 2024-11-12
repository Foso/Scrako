package de.jensklingenberg.scratch3.extension.pen

import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.model.BlockFull

private class EraseAll : Node {
    override fun visit(
        visitors: MutableMap<String, BlockFull>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
        visitors[identifier] = BlockSpec(
            opcode = "pen_clear",
        ).toBlock(nextUUID, parent)
    }
}

//https://en.scratch-wiki.info/wiki/Erase_All_(block)
fun CommonScriptBuilder.eraseAll() = addNode(EraseAll())