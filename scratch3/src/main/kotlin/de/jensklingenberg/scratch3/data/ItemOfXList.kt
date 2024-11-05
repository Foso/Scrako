package de.jensklingenberg.scratch3.data

import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scrako.common.setValue
import java.util.UUID

private class Itemoflist(val block0: ReporterBlock, val list: String) : ReporterBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
        val block0Id = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = "data_itemoflist",
            inputs = mapOf(
                "INDEX" to setValue(block0, block0Id, context)
            ),
            fields = mapOf(
                "LIST" to listOf(list, null)
            )
        ).toBlock(nextUUID, parent)
        block0.visit(visitors, identifier, block0Id, null, context)
    }
}

fun itemOfXList(index: ReporterBlock, list: ScratchList): ReporterBlock = Itemoflist(index, list.name)


