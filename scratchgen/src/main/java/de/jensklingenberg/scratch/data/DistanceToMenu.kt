package de.jensklingenberg.scratch.data

import de.jensklingenberg.scratch.common.Block
import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock
import java.util.UUID

class DistanceToMenu(private val destination: String) : Node, ReporterBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        index: Int,
        identifier: UUID,
        nextUUID: UUID?,
        layer: Int,
        context: Context
    ) {
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.sensing_distancetomenu,
            fields = mapOf("DISTANCETOMENU" to listOf(destination,null))
        ).toBlock(nextUUID?.toString(), parent, index == 0 && layer ==0)
    }
}

