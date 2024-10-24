package de.jensklingenberg.scratch.sensing

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.common.setValue
import de.jensklingenberg.scratch.looks.StringBlock
import de.jensklingenberg.scratch.model.Block
import de.jensklingenberg.scratch.operator.BooleanBlock
import java.util.UUID

private class TouchingColor(private val color: ReporterBlock) : BooleanBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {
        val destinationUUID = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.sensing_touchingcolor,
            inputs = mapOf(
                "COLOR" to setValue(color, destinationUUID)
            )
        ).toBlock(nextUUID, parent, context.topLevel)
        color.visit(visitors, identifier.toString(), destinationUUID, null, context)

    }
}

fun touchingColor(color: String): BooleanBlock = TouchingColor(StringBlock(color))

