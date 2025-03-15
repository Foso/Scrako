package de.jensklingenberg.scratch3.looks

import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.model.BlockFull

private class Backdrops(val backdrop: String) : ReporterBlock {
    override fun visit(
        visitors: MutableMap<String, BlockFull>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {

        visitors[identifier] = BlockSpec(
            opcode = "de.jensklingenberg.scratch3.looks.looks_backdrops",
            fields = mapOf(
                "BACKDROP" to listOf(backdrop, null)
            )
        ).toBlock(nextUUID, parent)

    }
}

internal fun looks_backdrops(backdrop: String): ReporterBlock = Backdrops(backdrop)
