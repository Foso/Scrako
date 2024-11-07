package de.jensklingenberg.scratch3.data

import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.model.BlockFull

class DistanceToMenu(private val destination: String) : ReporterBlock {
    override fun visit(
        visitors: MutableMap<String, BlockFull>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
        visitors[identifier] = BlockSpec(
            opcode = "sensing_distancetomenu",
            fields = mapOf("DISTANCETOMENU" to listOf(destination, null))
        ).toBlock(nextUUID, parent)
    }
}


