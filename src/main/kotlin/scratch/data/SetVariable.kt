package me.jens.scratch.data

import me.jens.scratch.common.Block
import me.jens.scratch.common.BlockSpec
import me.jens.scratch.common.Context
import me.jens.scratch.common.Node
import me.jens.scratch.common.OpCode
import me.jens.scratch.common.createLiteralMessage
import scratch.ScratchVariable
import java.util.UUID

class SetVariable(private val variable: ScratchVariable, private val item: String) : Node {
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
            opcode = OpCode.data_setvariableto,
            inputs = mapOf("VALUE" to createLiteralMessage(item)),
            fields = mapOf("VARIABLE" to listOf(variable.name, variable.id.toString()))
        ).toBlock(nextUUID?.toString(), parent, index == 0 && layer == 0)
    }
}

