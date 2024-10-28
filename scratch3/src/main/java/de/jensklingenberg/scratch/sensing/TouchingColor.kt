package de.jensklingenberg.scratch.sensing

import de.jensklingenberg.scrako.common.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.BooleanBlock
import de.jensklingenberg.scrako.common.ColorBlock
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scratch.common.OpCode
import java.util.UUID

private class TouchingColor(private val color: ReporterBlock) : BooleanBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context,

        ) {
        val destinationUUID = UUID.randomUUID()
        visitors[identifier.toString()] = BlockSpec(
            opcode = OpCode.sensing_touchingcolor,
            inputs = mapOf(
                "COLOR" to setValue(color, destinationUUID)
            )
        ).toBlock(nextUUID, parent)
        color.visit(visitors, identifier.toString(), destinationUUID, null, context)
    }
}

fun touchingColor(color: String): BooleanBlock = TouchingColor(ColorBlock(color))
fun touchingColor(color: ReporterBlock): BooleanBlock = TouchingColor(color)


