package de.jensklingenberg.scratch3.data

import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scratch3.common.OpCode

class DistanceToMenu(private val destination: String) : ReporterBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
        visitors[identifier] = BlockSpec(
            opcode = OpCode.sensing_distancetomenu,
            fields = mapOf("DISTANCETOMENU" to listOf(destination, null))
        ).toBlock(nextUUID, parent)
    }
}

