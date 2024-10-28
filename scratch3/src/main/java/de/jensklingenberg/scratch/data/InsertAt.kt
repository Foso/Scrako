package de.jensklingenberg.scratch.data

import de.jensklingenberg.scrako.common.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.common.StringBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scrako.common.StackBlock
import java.util.UUID

private class InsertAt(
    private val block: ReporterBlock,
    private val list: ScratchList,
    private val index: ReporterBlock
) : Node,
    StackBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context,

        ) {
        val indexBlockId = UUID.randomUUID()
        val dataBlockId = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.data_insertatlist,
            inputs = mapOf(
                "INDEX" to setValue(index, indexBlockId),
                "ITEM" to setValue(block, dataBlockId)
            ),
            fields = mapOf("LIST" to listOf(list.name, list.id.toString()))
        ).toBlock(nextUUID, parent)
        index.visit(visitors, identifier.toString(), indexBlockId, null, context)
        block.visit(visitors, identifier.toString(), dataBlockId, null, context)

    }
}

fun ScriptBuilder.insertAt(block: ReporterBlock, list: ScratchList, index: ReporterBlock) =
    addNode(InsertAt(block, list, index))

fun ScriptBuilder.insertAt(item: String, list: ScratchList, index: ReporterBlock) =
    addNode(InsertAt(StringBlock(item), list, index))

fun ScriptBuilder.insertAt(item: String, list: ScratchList, index: Int) =
    addNode(InsertAt(StringBlock(item), list, IntBlock(index)))
