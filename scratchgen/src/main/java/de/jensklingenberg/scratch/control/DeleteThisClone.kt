package de.jensklingenberg.scratch.control


import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.CapBlock
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.ScriptBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scrako.common.Block
import java.util.UUID

internal class DeleteThisClone : BlockSpec(
    opcode = OpCode.control_delete_this_clone,
), CapBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        if (nextUUID != null) {
            throw IllegalArgumentException("DeleteThisClone block cannot have a next block")
        }
        super.visit(visitors, parent, identifier, null, context)
    }
}

fun ScriptBuilder.deleteThisClone() = addChild(DeleteThisClone())