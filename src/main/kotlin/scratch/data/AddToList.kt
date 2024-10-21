package me.jens.scratch.data

import me.jens.createLiteralMessage
import me.jens.scratch.BlockSpecSpec
import me.jens.scratch.common.Node
import me.jens.scratch.common.OpCode
import scratch.Block
import scratch.List2
import java.util.UUID

class AddToList(private val item: String, private val list: List2) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        index: Int,
        name: UUID,
        nextUUID: UUID?,
        layer: Int
    ) {
        val next = nextUUID?.toString()
        visitors[name.toString()] = BlockSpecSpec(
            opcode = OpCode.data_addtolist,
            inputs = mapOf("ITEM" to createLiteralMessage(item)),
            fields = mapOf("LIST" to listOf(list.name,list.id.toString()))
        ).toBlock(next, parent, index == 0)
    }
}