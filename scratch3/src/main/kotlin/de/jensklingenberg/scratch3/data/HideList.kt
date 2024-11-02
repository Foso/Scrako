package de.jensklingenberg.scratch3.data

import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scrako.common.StackBlock
import de.jensklingenberg.scrako.model.Block

private class HideList(private val list: ScratchList) : Node, StackBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
        visitors[identifier] = BlockSpec(
            opcode = "data_hidelist",
            fields = mapOf("LIST" to listOf(list.name, list.id.toString()))
        ).toBlock(nextUUID, parent)
    }
}

fun CommonScriptBuilder.hideList(list: ScratchList) = addNode(HideList(list))
