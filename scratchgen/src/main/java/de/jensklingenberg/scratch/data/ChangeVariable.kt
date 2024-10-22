package de.jensklingenberg.scratch.data

import de.jensklingenberg.scratch.common.Block
import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.createLiteralMessage
import java.util.UUID

class ChangeVariable(private val variable: de.jensklingenberg.scratch.ScratchVariable, private val item: Int) : Node {
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
            opcode = OpCode.data_changevariableby,
            inputs = mapOf("VALUE" to createLiteralMessage(item.toString())),
            fields = mapOf("VARIABLE" to listOf(variable.name, variable.id.toString()))
        ).toBlock(nextUUID?.toString(), parent, index == 0 && layer == 0)
    }
}