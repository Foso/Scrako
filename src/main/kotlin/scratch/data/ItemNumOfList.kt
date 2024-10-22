package me.jens.scratch.data

import me.jens.scratch.common.Block
import me.jens.scratch.common.BlockSpec
import me.jens.scratch.common.Context
import me.jens.scratch.common.Node
import me.jens.scratch.common.OpCode
import me.jens.scratch.common.ReporterBlock
import me.jens.scratch.common.createLiteralMessage
import scratch.ScratchList
import java.util.UUID

class ItemNumOfList(private val item: Int, private val list: ScratchList) : Node, ReporterBlock {
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
            opcode = OpCode.data_itemnumoflist,
            inputs = mapOf("ITEM" to createLiteralMessage(item.toString())),
            fields = mapOf("LIST" to listOf(list.name, list.id.toString()))
        ).toBlock(nextUUID?.toString(), parent, index == 0 && layer ==0)
    }
}