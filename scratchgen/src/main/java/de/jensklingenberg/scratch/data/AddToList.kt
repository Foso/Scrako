package de.jensklingenberg.scratch.data


import de.jensklingenberg.scratch.common.Block
import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.createLiteralMessage
import java.util.UUID

class AddToList(private val item: String, private val list: de.jensklingenberg.scratch.ScratchList) : Node {
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
            opcode = OpCode.data_addtolist,
            inputs = mapOf("ITEM" to createLiteralMessage(item)),
            fields = mapOf("LIST" to listOf(list.name, list.id.toString()))
        ).toBlock(nextUUID?.toString(), parent, index == 0)
    }
}


