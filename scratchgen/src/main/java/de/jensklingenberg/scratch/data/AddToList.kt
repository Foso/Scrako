package de.jensklingenberg.scratch.data


import de.jensklingenberg.scratch.ScratchList
import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.common.setValue
import de.jensklingenberg.scratch.looks.StackBlock
import de.jensklingenberg.scratch.looks.StringBlock
import de.jensklingenberg.scratch.model.Block
import java.util.UUID

private class AddToList(private val block: ReporterBlock, private val list: ScratchList) : Node, StackBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        val childId = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.data_addtolist,
            inputs = mapOf("ITEM" to setValue(block, childId)),
            fields = mapOf("LIST" to listOf(list.name, list.id.toString()))
        ).toBlock(nextUUID, parent, context.topLevel)
        block.visit(visitors, identifier.toString(), childId, null, context)
    }
}


fun NodeBuilder.addToList(block: ReporterBlock, list: ScratchList) = addChild(AddToList(block, list))
fun NodeBuilder.addToList(item: String, list: ScratchList) = addChild(AddToList(StringBlock(item), list))
