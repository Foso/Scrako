package de.jensklingenberg.scratch3.sensing


import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.builder.Context
import de.jensklingenberg.scrako.common.DoubleBlock
import de.jensklingenberg.scrako.common.IntBlock
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.StringBlock
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.model.BlockFull
import java.util.UUID


private class Ask(
    val message: ReporterBlock,
) : Node {
    override fun visit(
        visitors: MutableMap<String, BlockFull>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context,
    ) {
        val messageId = UUID.randomUUID().toString()
        visitors[identifier] = BlockSpec(
            opcode = "sensing_askandwait",
            inputs = mapOf("QUESTION" to setValue(message, messageId, context) ),
            fields = mapOf()
        ).toBlock(nextUUID, identifier)
        message.visit(visitors, identifier, messageId, null, context)
    }
}

fun CommonScriptBuilder.ask(block: ReporterBlock) = addNode(Ask(block))
fun CommonScriptBuilder.ask(message: String) = addNode(Ask(StringBlock(message)))
fun CommonScriptBuilder.ask(message: Int) = addNode(Ask(IntBlock(message)))
fun CommonScriptBuilder.ask(message: Double) = addNode(Ask(DoubleBlock(message)))


