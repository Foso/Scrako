package de.jensklingenberg.scratch3.data

import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scrako.common.StackBlock
import de.jensklingenberg.scrako.common.StringBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.BlockFull
import java.util.UUID

private class InsertAt(
    private val block: ReporterBlock,
    private val list: ScratchList,
    private val index: ReporterBlock
) : Node,
    StackBlock {
    override fun visit(
        visitors: MutableMap<String, BlockFull>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
        val indexBlockId = UUID.randomUUID().toString()
        val dataBlockId = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = "data_insertatlist",
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

fun CommonScriptBuilder.insertAt(block: ReporterBlock, list: ScratchList, index: ReporterBlock) =
    addNode(InsertAt(block, list, index))

fun CommonScriptBuilder.insertAt(item: String, list: ScratchList, index: ReporterBlock) =
    addNode(InsertAt(StringBlock(item), list, index))

fun CommonScriptBuilder.insertAt(item: String, list: ScratchList, index: Int) =
    addNode(InsertAt(StringBlock(item), list, IntBlock(index)))
