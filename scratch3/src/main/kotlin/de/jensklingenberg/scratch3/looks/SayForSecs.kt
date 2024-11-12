package de.jensklingenberg.scratch3.looks

import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.builder.SpriteScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.DoubleBlock
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.StringBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.BlockFull
import java.util.UUID


private class SayForSecs(
    val message: ReporterBlock,
    val secs: ReporterBlock
) : Node {
    override fun visit(
        visitors: MutableMap<String, BlockFull>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context,
    ) {
        val messageId = UUID.randomUUID().toString()
        val secsId = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = "looks_sayforsecs",
            inputs = mapOf("MESSAGE" to setValue(message, messageId, context),
                "SECS" to setValue(secs, secsId, context)),
            fields = mapOf()
        ).toBlock(nextUUID, identifier)
        message.visit(visitors, identifier, messageId, null, context)
        secs.visit(visitors, identifier, secsId, null, context)
    }
}

fun SpriteScriptBuilder.sayForSecs(block: ReporterBlock, secs: ReporterBlock) = addNode(SayForSecs(block,secs))
fun SpriteScriptBuilder.sayForSecs(message: String, secs: ReporterBlock) = addNode(SayForSecs(StringBlock(message),secs))
fun SpriteScriptBuilder.sayForSecs(message: Int, secs: ReporterBlock) = addNode(SayForSecs(IntBlock(message),secs))
fun SpriteScriptBuilder.sayForSecs(message: Double, secs: ReporterBlock) = addNode(SayForSecs(DoubleBlock(message),secs))


