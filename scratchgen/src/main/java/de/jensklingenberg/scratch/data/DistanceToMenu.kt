package de.jensklingenberg.scratch.data

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.model.Block
import java.util.UUID

class DistanceToMenu(private val destination: String) : Node, ReporterBlock {
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
        ).toBlock(nextUUID?.toString(), parent, context.topLevel)
    }
}

