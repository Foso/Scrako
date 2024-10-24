package de.jensklingenberg.scratch.data

import de.jensklingenberg.scratch.ScratchList
import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.common.setValue
import de.jensklingenberg.scratch.looks.StackBlock
import de.jensklingenberg.scratch.model.Block
import java.util.UUID

private class ListContains(private val list: ScratchList, private val block: ReporterBlock) : Node, ReporterBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        val childId = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.data_listcontainsitem,
            inputs = mapOf("ITEM" to setValue(block, childId)),
            fields = mapOf("LIST" to listOf(list.name, list.id.toString()))
        ).toBlock(nextUUID, parent, context.topLevel)
        block.visit(visitors, identifier.toString(), childId, null, context)
    }
}

fun listContains(list: ScratchList, block: ReporterBlock) : ReporterBlock = ListContains(list, block)