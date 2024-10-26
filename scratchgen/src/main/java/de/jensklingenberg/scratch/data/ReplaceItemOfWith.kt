package de.jensklingenberg.scratch.data


import de.jensklingenberg.scratch.ScratchList
import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.IntBlock
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.common.setValue
import de.jensklingenberg.scratch.looks.StackBlock
import de.jensklingenberg.scratch.looks.StringBlock
import de.jensklingenberg.scratch.model.Block
import java.util.UUID

private class ReplaceItemOfWith(
    private val index: ReporterBlock,
    private val list: ScratchList,
    private val replace: ReporterBlock
) : Node, StackBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        val indexUUID = UUID.randomUUID()
        val replaceUUID = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.data_replaceitemoflist,
            inputs = mapOf(
                "INDEX" to setValue(index, indexUUID),
                "ITEM" to setValue(replace, replaceUUID)
            ),
            fields = mapOf("LIST" to listOf(list.name, list.id.toString()))
        ).toBlock(nextUUID, parent)
        index.visit(visitors, identifier.toString(), indexUUID, null, context.copy(topLevel = false))
        replace.visit(visitors, identifier.toString(), replaceUUID, null, context.copy(topLevel = false))
    }
}

fun NodeBuilder.replaceItemOfWith(index: ReporterBlock, list: ScratchList, replace: ReporterBlock) =
    addChild(ReplaceItemOfWith(index, list, replace))

fun NodeBuilder.replaceItemOfWith(index: Int, list: ScratchList, replace: String) =
    addChild(ReplaceItemOfWith(IntBlock(index), list, StringBlock(replace)))

