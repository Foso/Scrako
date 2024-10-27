package de.jensklingenberg.scratch.looks


import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ScriptBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.ScratchVariable
import de.jensklingenberg.scrako.common.createLiteralMessage
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scrako.common.Block
import de.jensklingenberg.scrako.common.ScratchType
import de.jensklingenberg.scrako.common.StringBlock
import de.jensklingenberg.scrako.common.createBlockRef
import de.jensklingenberg.scrako.common.getScratchType
import java.util.UUID


private data class Say(private val content: ReporterBlock, private val seconds: Int? = null) : Node, StackBlock {

    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
    ) {

        val operatorUUID = UUID.randomUUID()

        val inputMap = mutableMapOf(
            "MESSAGE" to when (content) {
                is StringBlock -> createLiteralMessage(content.value)
                is ScratchVariable -> setValue(content, operatorUUID)
                is ScratchList -> setValue(content, operatorUUID)

                else -> {
                    createBlockRef(operatorUUID.toString())
                }
            }
        )

        if (seconds != null) {
            inputMap["SECS"] = getScratchType(seconds.toString(), ScratchType.POSITIVE_NUMBER)
        }

        val opCode = when (seconds) {
            null -> OpCode.LooksSay
            else -> OpCode.looks_sayforsecs
        }
        val spec = BlockSpec(
            opcode = opCode,
            inputs = inputMap
        )
        visitors[identifier.toString()] = spec.toBlock(nextUUID, parent)

        content.visit(
            visitors,
            identifier.toString(),
            operatorUUID,
            null,
            context.copy(topLevel = false)
        )

    }


}

sealed interface LooksSayContent {
    class Literal(val message: String) : LooksSayContent
    class Reporter(val operatorSpec: ReporterBlock) : LooksSayContent
}




fun ScriptBuilder.say(reporterBlock: ReporterBlock) = addChild(Say(reporterBlock))
fun ScriptBuilder.say(message: Double, seconds: Int? = null) = say(message.toString(), seconds)
fun ScriptBuilder.say(message: String, seconds: Int? = null) = addChild(Say(StringBlock(message), seconds))