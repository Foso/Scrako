package de.jensklingenberg.scratch.data


import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scrako.common.StackBlock
import de.jensklingenberg.scrako.common.StringBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scratch.common.OpCode
import java.util.UUID

private class ReplaceItemOfWith(
    private val index: ReporterBlock,
    private val list: ScratchList,
    private val replace: ReporterBlock
) : Node, StackBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context,

        ) {
        val indexUUID = UUID.randomUUID().toString()
        val replaceUUID = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = OpCode.data_replaceitemoflist,
            inputs = mapOf(
                "INDEX" to setValue(index, indexUUID, context),
                "ITEM" to setValue(replace, replaceUUID, context)
            ),
            fields = mapOf("LIST" to listOf(list.name, list.id.toString()))
        ).toBlock(nextUUID, parent)
        index.visit(visitors, identifier, indexUUID, null, context)
        replace.visit(visitors, identifier, replaceUUID, null, context)
    }
}

fun ScriptBuilder.replaceItemOfWith(index: ReporterBlock, list: ScratchList, replace: ReporterBlock) =
    addNode(ReplaceItemOfWith(index, list, replace))

fun ScriptBuilder.replaceItemOfWith(index: Int, list: ScratchList, replace: String) =
    addNode(ReplaceItemOfWith(IntBlock(index), list, StringBlock(replace)))

