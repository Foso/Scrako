package de.jensklingenberg.scratch3.looks


import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.builder.SpriteScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.DoubleBlock
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.SpriteBlock
import de.jensklingenberg.scrako.common.StringBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.BlockFull
import java.util.UUID


private class Say(
    private val message: ReporterBlock,
) : Node, SpriteBlock {
    override fun visit(
        visitors: MutableMap<String, BlockFull>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context,
    ) {
        val messageId = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = "looks_say",
            inputs = mapOf("MESSAGE" to setValue(message, messageId, context)),
            fields = mapOf()
        ).toBlock(nextUUID, identifier)
        message.visit(visitors, identifier, messageId, null, context)
    }
}

fun SpriteScriptBuilder.say(block: ReporterBlock) = addNode(Say(block))
fun SpriteScriptBuilder.say(message: String) = addNode(Say(StringBlock(message)))
fun SpriteScriptBuilder.say(message: Int) = addNode(Say(IntBlock(message)))
fun SpriteScriptBuilder.say(message: Double) = addNode(Say(DoubleBlock(message)))
