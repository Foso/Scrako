package de.jensklingenberg.scratch3.looks

import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.Block
import java.util.UUID

/**
 * https://en.scratch-wiki.info/wiki/Think_()_(block)
 */
private class Thinkforsecs(
    val block0: ReporterBlock,
    val block1: ReporterBlock,
) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context
    ) {
        val block0Id = UUID.randomUUID().toString()
        val block1Id = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = "looks_thinkforsecs",
            inputs = mapOf(
                "MESSAGE" to setValue(block0, block0Id, context),
                "SECS" to setValue(block1, block1Id, context)
            ),
            fields = mapOf(

            )
        ).toBlock(nextUUID, parent)
        block0.visit(visitors, identifier, block0Id, null, context)
        block1.visit(visitors, identifier, block1Id, null, context)
    }
}

fun CommonScriptBuilder.thinkforsecs(block0: ReporterBlock, block1: ReporterBlock) = addNode(Thinkforsecs(block0, block1))