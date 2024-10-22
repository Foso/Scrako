package me.jens.scratch.data

import me.jens.scratch.common.Block
import me.jens.scratch.common.BlockSpec
import me.jens.scratch.common.Context
import me.jens.scratch.common.Node
import me.jens.scratch.common.OpCode
import me.jens.scratch.common.ReporterBlock
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

