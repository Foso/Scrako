package de.jensklingenberg.scratch3.looks

import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.builder.SpriteScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.BlockFull
import java.util.UUID

/**
 * https://en.scratch-wiki.info/wiki/Think_()_(block)
 */
private class Thinkforsecs(
    val messageBlock: ReporterBlock,
    val secsBlock: ReporterBlock,
) : Node {
    override fun visit(
        visitors: MutableMap<String, BlockFull>,
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
                "MESSAGE" to setValue(messageBlock, block0Id, context),
                "SECS" to setValue(secsBlock, block1Id, context)
            ),
            fields = mapOf(

            )
        ).toBlock(nextUUID, parent)
        messageBlock.visit(visitors, identifier, block0Id, null, context)
        secsBlock.visit(visitors, identifier, block1Id, null, context)
    }
}

fun SpriteScriptBuilder.thinkForSecs(messageBlock: ReporterBlock, secsBlock: ReporterBlock) = addNode(Thinkforsecs(messageBlock, secsBlock))