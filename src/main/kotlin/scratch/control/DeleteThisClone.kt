package me.jens.scratch.control

import me.jens.scratch.common.Block
import me.jens.scratch.common.BlockSpec
import me.jens.scratch.common.CapBlock
import me.jens.scratch.common.Context
import me.jens.scratch.common.OpCode
import java.util.UUID

class DeleteThisClone() : BlockSpec(
    opcode = OpCode.control_delete_this_clone,
) , CapBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        index: Int,
        identifier: UUID,
        nextUUID: UUID?,
        layer: Int,
        context: Context
    ) {
        if (nextUUID != null) {
            throw IllegalArgumentException("DeleteThisClone block cannot have a next block")
        }
        super.visit(visitors, parent, index, identifier, null, layer, context)
    }
}