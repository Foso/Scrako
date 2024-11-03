package de.jensklingenberg.scratch3.looks


import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.DoubleBlock
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scrako.common.ScratchType
import de.jensklingenberg.scrako.common.ScratchVariable
import de.jensklingenberg.scrako.common.StackBlock
import de.jensklingenberg.scrako.common.StringBlock
import de.jensklingenberg.scrako.common.createMessage
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.Block
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import java.util.UUID


private class Say(
    public val message: ReporterBlock,
) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context,
    ) {
        val messageId = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = "looks_say",
            inputs = mapOf("MESSAGE" to setValue(message, messageId, context) ),
            fields = mapOf()
        ).toBlock(nextUUID, identifier)
        message.visit(visitors, identifier, messageId, null, context)
    }
}

fun CommonScriptBuilder.say(block: ReporterBlock) = addNode(Say(block))
fun CommonScriptBuilder.say(message: String) = addNode(Say(StringBlock(message)))
fun CommonScriptBuilder.say(message: Int) = addNode(Say(IntBlock(message)))
fun CommonScriptBuilder.say(message: Double) = addNode(Say(DoubleBlock(message)))
