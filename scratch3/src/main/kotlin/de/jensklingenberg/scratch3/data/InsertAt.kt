package de.jensklingenberg.scratch3.data

import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scrako.common.StackBlock
import de.jensklingenberg.scrako.common.StringBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scratch3.common.OpCode
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
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
        val indexBlockId = UUID.randomUUID().toString()
        val dataBlockId = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = OpCode.data_insertatlist,
            inputs = mapOf(
                "INDEX" to setValue(index, indexBlockId, context),
                "ITEM" to setValue(block, dataBlockId, context)
            ),
            fields = mapOf("LIST" to listOf(list.name, list.id.toString()))
        ).toBlock(nextUUID, parent)
        index.visit(visitors, identifier, indexBlockId, null, context)
        block.visit(visitors, identifier, dataBlockId, null, context)
    }
}

fun ScriptBuilder.insertAt(block: ReporterBlock, list: ScratchList, index: ReporterBlock) =
    addNode(InsertAt(block, list, index))

fun ScriptBuilder.insertAt(item: String, list: ScratchList, index: ReporterBlock) =
    addNode(InsertAt(StringBlock(item), list, index))

fun ScriptBuilder.insertAt(item: String, list: ScratchList, index: Int) =
    addNode(InsertAt(StringBlock(item), list, IntBlock(index)))
