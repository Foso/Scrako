package de.jensklingenberg.scratch3.sensing

import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.BooleanBlock
import de.jensklingenberg.scrako.common.ColorBlock
import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.Block
import java.util.UUID

private class TouchingColor(private val color: ReporterBlock) : BooleanBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
        val destinationUUID = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = "sensing_touchingcolor",
            inputs = mapOf(
                "COLOR" to setValue(color, destinationUUID, context)
            )
        ).toBlock(nextUUID, parent)
        color.visit(visitors, identifier, destinationUUID, null, context)
    }
}

fun touchingColor(color: String): BooleanBlock = TouchingColor(ColorBlock(color))
fun touchingColor(color: ReporterBlock): BooleanBlock = TouchingColor(color)



//sensing_touchingobject

class TouchingObject(val s: String) : BooleanBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
        visitors[identifier] = BlockSpec(
            opcode = "sensing_touchingobject",
            fields = mapOf("TOUCHINGOBJECTMENU" to listOf(s, null))
        ).toBlock(nextUUID, parent)
    }
}

fun touchingObject(s: String): BooleanBlock = TouchingObject(s)
