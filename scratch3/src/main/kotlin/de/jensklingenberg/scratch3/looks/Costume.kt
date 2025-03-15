package de.jensklingenberg.scratch3.looks

import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.model.BlockFull

private class Costume(val backdrop: String) : ReporterBlock {
    override fun visit(
        visitors: MutableMap<String, BlockFull>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {

        visitors[identifier] = BlockSpec(
            opcode = "looks_costumenumbername",
            fields = mapOf(
                "NUMBER_NAME" to listOf(backdrop, null)
            )
        ).toBlock(nextUUID, parent)

    }
}

fun costumeNumber(): ReporterBlock = Costume("number")
fun costumeName(): ReporterBlock = Costume("name")