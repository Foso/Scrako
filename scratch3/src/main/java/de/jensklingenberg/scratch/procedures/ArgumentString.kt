package de.jensklingenberg.scratch.procedures

import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.model.Block

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