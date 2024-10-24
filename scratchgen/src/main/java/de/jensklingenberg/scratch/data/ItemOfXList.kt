package de.jensklingenberg.scratch.data

import de.jensklingenberg.scratch.ScratchList
import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.common.createMessage
import de.jensklingenberg.scratch.model.Block
import java.util.UUID

private class ItemOfXList(private val index: Int, private val list: ScratchList) : ReporterBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.data_itemoflist,
            inputs = mapOf(
                "INDEX" to createMessage(1, 7, this.index.toString()),
            ),
            fields = mapOf("LIST" to listOf(list.name, list.id.toString()))
        ).toBlock(nextUUID, parent, context.topLevel)
    }
}

fun NodeBuilder.itemOfXList(index: Int, list: ScratchList) =
    addChild(ItemOfXList(index, list))


