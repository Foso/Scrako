package de.jensklingenberg.scratch.data

import de.jensklingenberg.scratch.ScratchList
import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.looks.StackBlock
import de.jensklingenberg.scratch.model.Block
import java.util.UUID

private class ShowList(private val list: ScratchList) : Node, StackBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.data_showlist,
            fields = mapOf("LIST" to listOf(list.name, list.id.toString()))
        ).toBlock(nextUUID, parent)
    }
}

fun NodeBuilder.showList(list: ScratchList) = addChild(ShowList(list))
