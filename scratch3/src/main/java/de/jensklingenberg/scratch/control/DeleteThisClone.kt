package de.jensklingenberg.scratch.control


import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.common.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.CapBlock
import de.jensklingenberg.scrako.common.Context

internal class DeleteThisClone : BlockSpec(
    opcode = "control_delete_this_clone",
), CapBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
        if (nextUUID != null) {
            throw IllegalArgumentException("DeleteThisClone block cannot have a next block")
        }
        super.visit(visitors, parent, identifier, null, context)
    }
}

fun ScriptBuilder.deleteThisClone() = addNode(DeleteThisClone())