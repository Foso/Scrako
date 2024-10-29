package de.jensklingenberg.scratch.procedures

import de.jensklingenberg.scrako.common.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scratch.common.OpCode
import java.util.UUID

class ArgumentBoolean(override val name: String, override val defaultValue: String = "") : Argument {
    override val id: UUID = UUID.randomUUID()

    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context,
    ) {
        visitors[identifier] = BlockSpec(
            opcode = OpCode.argument_reporter_boolean,
            fields = mapOf("VALUE" to listOf(name, null))
        ).toBlock(null, parent)
    }
}