package de.jensklingenberg.scratch.data


import de.jensklingenberg.scratch.ScratchList
import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.createLiteralMessage
import de.jensklingenberg.scratch.common.createMessage
import de.jensklingenberg.scratch.model.Block
import java.util.UUID

private  class ReplaceItemOfWith(
    private val index: Int,
    private val list: ScratchList,
    private val replace: String
) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.data_replaceitemoflist,
            inputs = mapOf(
                "INDEX" to createMessage(1, 7, this.index.toString()),
                "ITEM" to createLiteralMessage(replace)
            ),
            fields = mapOf("LIST" to listOf(list.name, list.id.toString()))
        ).toBlock(nextUUID, parent, index == 0)
    }
}

fun NodeBuilder.replaceItemOfWith(index: Int, list: ScratchList, replace: String) =
    addChild(ReplaceItemOfWith(index, list, replace))