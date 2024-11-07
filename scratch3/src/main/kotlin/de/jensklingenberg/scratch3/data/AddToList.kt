package de.jensklingenberg.scratch3.data


import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scrako.common.StackBlock
import de.jensklingenberg.scrako.common.StringBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.BlockFull
import java.util.UUID

private class AddToList(private val list: ScratchList, private val block: ReporterBlock) : Node, StackBlock {
    override fun visit(
        visitors: MutableMap<String, BlockFull>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context,

        ) {
        val childId = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = "data_addtolist",
            inputs = mapOf("ITEM" to setValue(block, childId, context)),
            fields = mapOf("LIST" to listOf(list.name, list.id.toString()))
        ).toBlock(nextUUID, parent)
        block.visit(visitors, identifier, childId, null, context)
    }
}


fun CommonScriptBuilder.addToList(list: ScratchList, block: ReporterBlock) = addNode(AddToList(list, block))
fun CommonScriptBuilder.addToList(list: ScratchList, item: String) = addNode(AddToList(list, StringBlock(item)))
