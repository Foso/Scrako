package de.jensklingenberg.scratch.data

import de.jensklingenberg.scratch.common.Block
import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.ScratchList
import java.util.UUID

class LengthOfList(private val list: de.jensklingenberg.scratch.ScratchList) : Node, ReporterBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        index: Int,
        identifier: UUID,
        nextUUID: UUID?,
        layer: Int,
        context: Context
    ) {
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.data_lengthoflist,
            fields = mapOf("LIST" to listOf(list.name, list.id.toString()))
        ).toBlock(nextUUID?.toString(), parent, index == 0)
    }
}