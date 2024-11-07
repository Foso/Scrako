package de.jensklingenberg.scratch3.data

import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.common.ListBlock
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scrako.model.BlockFull

private class LengthOfList(private val list: ScratchList) : ReporterBlock, ListBlock {
    override fun visit(
        visitors: MutableMap<String, BlockFull>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context,

        ) {
        visitors[identifier] = BlockSpec(
            opcode = "data_lengthoflist",
            fields = mapOf("LIST" to listOf(list.name, list.id.toString()))
        ).toBlock(nextUUID, parent)
    }
}

fun lengthOfList(list: ScratchList): ReporterBlock = LengthOfList(list)