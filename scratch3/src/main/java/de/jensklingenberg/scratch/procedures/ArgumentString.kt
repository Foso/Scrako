package de.jensklingenberg.scratch.procedures

import de.jensklingenberg.scrako.common.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import java.util.UUID

class ArgumentString(override val name: String, override val defaultValue: String = "") : Argument {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context,
    ) {
        visitors[identifier] = BlockSpec(
            opcode = "argument_reporter_string_number",
            fields = mapOf("VALUE" to listOf(name, null))
        ).toBlock(null, parent)
    }
}

fun addArgumentString(name: String, defaultValue: String = "") = ArgumentString(name, defaultValue)