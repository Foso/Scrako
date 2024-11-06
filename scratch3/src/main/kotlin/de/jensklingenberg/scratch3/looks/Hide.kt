package de.jensklingenberg.scratch3.looks

import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.Block
import java.util.UUID

private class Hide : BlockSpec(
    opcode = "looks_hide",
)

fun CommonScriptBuilder.hide() = addNode(Hide())



//looks_goforwardbackwardlayers

class GoForwardBackwardLayers(private val value: String, val numbBlock: ReporterBlock) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
        val numId = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = "looks_goforwardbackwardlayers",
            inputs = mapOf("NUM" to setValue(numbBlock, numId,context)),
            fields = mapOf("FORWARDBACKWARD" to listOf(value, null))
        ).toBlock(nextUUID, parent)
        numbBlock.visit(visitors, identifier, numId, null, context)
    }
}

fun CommonScriptBuilder.goForwardLayers(value: ReporterBlock) = addNode(GoForwardBackwardLayers("forward",value))
fun CommonScriptBuilder.goBackwardLayers(value: ReporterBlock) = addNode(GoForwardBackwardLayers("backward",value))