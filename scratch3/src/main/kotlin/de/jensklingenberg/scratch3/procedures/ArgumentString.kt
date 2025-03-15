package de.jensklingenberg.scratch3.procedures

import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.model.BlockFull

internal class ArgumentString(override val name: String, override val defaultValue: String = "") : Argument {
    override fun visit(
        visitors: MutableMap<String, BlockFull>,
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

internal fun addArgumentString(name: String, defaultValue: String = "") = ArgumentString(name, defaultValue)