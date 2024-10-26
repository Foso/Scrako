package de.jensklingenberg.scratch.looks


import de.jensklingenberg.scratch.ScratchList
import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.common.ScratchType
import de.jensklingenberg.scratch.common.ScratchVariable
import de.jensklingenberg.scratch.common.createBlockRef
import de.jensklingenberg.scratch.common.createLiteralMessage
import de.jensklingenberg.scratch.common.getScratchType
import de.jensklingenberg.scratch.common.setValue
import de.jensklingenberg.scratch.model.Block
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

class StringBlock(val value: String) : ReporterBlock
class ColorBlock(val value: String) : ReporterBlock


fun NodeBuilder.say(reporterBlock: ReporterBlock) = addChild(Say(reporterBlock))
fun NodeBuilder.say(message: Double, seconds: Int? = null) = say(message.toString(), seconds)
fun NodeBuilder.say(message: String, seconds: Int? = null) = addChild(Say(StringBlock(message), seconds))