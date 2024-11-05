package de.jensklingenberg.scratch3.procedures

import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.model.Block

class ArgumentBoolean(override val name: String, override val defaultValue: String = "") : Argument, ReporterBlock {

    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context,
    ) {
        visitors[identifier] = BlockSpec(
            opcode = "argument_reporter_boolean",
            fields = mapOf("VALUE" to listOf(name, null))
        ).toBlock(null, parent)
    }
}

fun addArgumentBoolean(name: String, defaultValue: String = "") = ArgumentBoolean(name, defaultValue)