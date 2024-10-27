package de.jensklingenberg.scratch.data


import de.jensklingenberg.scrako.common.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scrako.common.ScriptBuilder
import de.jensklingenberg.scrako.common.StringBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scrako.common.StackBlock
import java.util.UUID

private class AddToList(private val list: ScratchList, private val block: ReporterBlock) : Node, StackBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context,

        ) {
        val childId = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.data_addtolist,
            inputs = mapOf("ITEM" to setValue(block, childId)),
            fields = mapOf("LIST" to listOf(list.name, list.id.toString()))
        ).toBlock(nextUUID, parent)
        block.visit(visitors, identifier.toString(), childId, null, context)
    }
}


fun ScriptBuilder.addToList(list: ScratchList, block: ReporterBlock) = addChild(AddToList(list, block))
fun ScriptBuilder.addToList(list: ScratchList, item: String) = addChild(AddToList(list, StringBlock(item)))
