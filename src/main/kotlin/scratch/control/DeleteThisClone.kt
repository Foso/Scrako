package me.jens.scratch.control

import me.jens.scratch.BlockSpecSpec
import me.jens.scratch.common.CapBlock
import me.jens.scratch.common.OpCode
import scratch.Block
import java.util.UUID

class DeleteThisClone() : BlockSpecSpec(
    opcode = OpCode.control_delete_this_clone,
) , CapBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        index: Int,
        name: UUID,
        nextUUID: UUID?,
        layer: Int
    ) {
        if (nextUUID != null) {
            throw IllegalArgumentException("DeleteThisClone block cannot have a next block")
        }
        super.visit(visitors, parent, index, name, null, layer)
    }
}