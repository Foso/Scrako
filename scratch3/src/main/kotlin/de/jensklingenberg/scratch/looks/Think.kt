package de.jensklingenberg.scratch.looks

import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.StackBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.Block
import java.util.UUID

/**
 * https://en.scratch-wiki.info/wiki/Think_()_(block)
 */
private class Think(val block0 : ReporterBlock, ) : Node, StackBlock {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
        val block0Id = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = "looks_think",
            inputs = mapOf(
                "MESSAGE" to setValue(block0, block0Id, context) 
            ),
            fields = mapOf(
                
            )
        ).toBlock(nextUUID, parent)
        block0.visit(visitors, identifier, block0Id, null, context)
    }
}

fun ScriptBuilder.think(block0: ReporterBlock) = addNode(Think(block0))