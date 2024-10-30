package de.jensklingenberg.scratch.data

import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.BooleanBlock
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scrako.common.setValue
import java.util.UUID

private class ListContains(private val list: ScratchList, private val block: ReporterBlock) : BooleanBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context,
    ) {
        val childId = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = "data_listcontainsitem",
            inputs = mapOf("ITEM" to setValue(block, childId, context)),
            fields = mapOf("LIST" to listOf(list.name, list.id.toString()))
        ).toBlock(nextUUID, parent)
        block.visit(visitors, identifier, childId, null, context)
    }
}

fun listContains(list: ScratchList, block: ReporterBlock): BooleanBlock = ListContains(list, block)