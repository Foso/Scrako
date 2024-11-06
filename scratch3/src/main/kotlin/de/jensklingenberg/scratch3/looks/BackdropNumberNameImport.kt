package de.jensklingenberg.scratch3.looks

import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.model.Block

private class BackdropNumberNameImport(private val backdrop: String) : ReporterBlock {

    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
        visitors[identifier] = BlockSpec(
            opcode = "looks_backdropnumbername",
            fields = mapOf(
                "NUMBER_NAME" to listOf(backdrop, null)
            )
        ).toBlock(nextUUID, parent)
    }
}

fun backdropNumber(): ReporterBlock = BackdropNumberNameImport("number")
fun backdropName(): ReporterBlock = BackdropNumberNameImport("name")