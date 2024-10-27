package de.jensklingenberg.scratch.data

import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.common.Block
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.ScratchList
import java.util.UUID

private class ItemNumOfList(private val item: ReporterBlock, private val list: ScratchList) : Node,
    ReporterBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        val itemUUID = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.data_itemnumoflist,
            inputs = mapOf("ITEM" to setValue(item, itemUUID)),
            fields = mapOf("LIST" to listOf(list.name, list.id.toString()))
        ).toBlock(nextUUID, parent)
        item.visit(visitors, identifier.toString(), itemUUID, null, context)
    }
}

fun itemNumOfList(item: ReporterBlock, list: ScratchList): ReporterBlock = ItemNumOfList(item, list)
fun itemNumOfList(item: Int, list: ScratchList): ReporterBlock = ItemNumOfList(IntBlock(item), list)