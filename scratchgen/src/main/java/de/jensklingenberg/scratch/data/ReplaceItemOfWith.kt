package de.jensklingenberg.scratch.data


import de.jensklingenberg.scrako.common.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scrako.common.ScriptBuilder
import de.jensklingenberg.scrako.common.StringBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.looks.StackBlock
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
        context: Context,

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
        index.visit(visitors, identifier.toString(), indexUUID, null, context)
        replace.visit(visitors, identifier.toString(), replaceUUID, null, context)
    }
}

fun ScriptBuilder.replaceItemOfWith(index: ReporterBlock, list: ScratchList, replace: ReporterBlock) =
    addChild(ReplaceItemOfWith(index, list, replace))

fun ScriptBuilder.replaceItemOfWith(index: Int, list: ScratchList, replace: String) =
    addChild(ReplaceItemOfWith(IntBlock(index), list, StringBlock(replace)))

