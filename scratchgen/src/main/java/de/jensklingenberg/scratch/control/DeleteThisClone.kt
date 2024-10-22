package de.jensklingenberg.scratch.control


import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.CapBlock
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.model.Block
import java.util.UUID

class DeleteThisClone() : BlockSpec(
    opcode = OpCode.control_delete_this_clone,
), CapBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        layer: Int,
        context: Context
    ) {
        if (nextUUID != null) {
            throw IllegalArgumentException("DeleteThisClone block cannot have a next block")
        }
        super.visit(visitors, parent, identifier, null, layer, context)
    }
}