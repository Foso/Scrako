package de.jensklingenberg.scratch.motion

import de.jensklingenberg.scrako.common.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.MotionBlock
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.setValue
import java.util.UUID

internal sealed class Turn(val opcode: String, private val reporterBlock: ReporterBlock) : Node, MotionBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context,

        ) {
        val operatorUUID = UUID.randomUUID().toString()

        visitors[identifier] = BlockSpec(
            opcode = opcode,
            inputs = mapOf(
                "DEGREES" to setValue(reporterBlock, operatorUUID, context)
            )
        ).toBlock(nextUUID, parent)
        reporterBlock.visit(visitors, identifier, operatorUUID, null, context)
    }
}

