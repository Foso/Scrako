package de.jensklingenberg.scratch3.data

import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.BlockFull
import java.util.UUID

private class ItemNumOfList(private val block0: ReporterBlock, private val list: ScratchList) : Node,
    ReporterBlock {
    override fun visit(
        visitors: MutableMap<String, BlockFull>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context,

        ) {
        val itemUUID = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = "data_itemnumoflist",
            inputs = mapOf("ITEM" to setValue(block0, itemUUID, context)),
            fields = mapOf("LIST" to listOf(list.name, list.id.toString()))
        ).toBlock(nextUUID, parent)
        block0.visit(visitors, identifier, itemUUID, null, context)
    }
}

fun itemNumOfList(item: ReporterBlock, list: ScratchList): ReporterBlock = ItemNumOfList(item, list)
fun itemNumOfList(item: Int, list: ScratchList): ReporterBlock = ItemNumOfList(IntBlock(item), list)
fun ScratchList.indexOf(item: ReporterBlock): ReporterBlock = ItemNumOfList(item, this)
fun ScratchList.indexOf(item: Int): ReporterBlock = ItemNumOfList(IntBlock(item), this)