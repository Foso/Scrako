package me.jens.scratch.data

import me.jens.scratch.Block
import me.jens.scratch.BlockSpecSpec
import me.jens.scratch.common.Context
import me.jens.scratch.common.Node
import me.jens.scratch.common.OpCode
import scratch.ScratchList
import java.util.UUID

class DeleteAllOf(private val list: ScratchList) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        index: Int,
        name: UUID,
        nextUUID: UUID?,
        layer: Int,
        context: Context
    ) {
        visitors[name.toString()] = BlockSpecSpec(
            opcode = OpCode.data_deletealloflist,
            fields = mapOf("LIST" to listOf(list.name, list.id.toString()))
        ).toBlock(nextUUID?.toString(), parent, index == 0)
    }
}