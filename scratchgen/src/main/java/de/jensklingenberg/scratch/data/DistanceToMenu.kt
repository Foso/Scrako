package de.jensklingenberg.scratch.data

import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.Block
import java.util.UUID

class DistanceToMenu(private val destination: String) : ReporterBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.sensing_distancetomenu,
            fields = mapOf("DISTANCETOMENU" to listOf(destination, null))
        ).toBlock(nextUUID, parent)
    }
}

