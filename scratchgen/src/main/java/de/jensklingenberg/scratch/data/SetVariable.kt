package de.jensklingenberg.scratch.data

import de.jensklingenberg.scratch.ScratchVariable
import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.createLiteralMessage
import de.jensklingenberg.scratch.model.Block
import java.util.UUID

class SetVariable(private val variable: ScratchVariable, private val item: String) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.data_setvariableto,
            inputs = mapOf("VALUE" to createLiteralMessage(item)),
            fields = mapOf("VARIABLE" to listOf(variable.name, variable.id.toString()))
        ).toBlock(nextUUID?.toString(), parent, context.topLevel)
    }
}

