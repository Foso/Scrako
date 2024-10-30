package de.jensklingenberg.scratch.looks


import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.ScratchList
import de.jensklingenberg.scrako.common.ScratchType
import de.jensklingenberg.scrako.common.ScratchVariable
import de.jensklingenberg.scrako.common.StackBlock
import de.jensklingenberg.scrako.common.StringBlock
import de.jensklingenberg.scrako.common.createMessage
import de.jensklingenberg.scrako.common.setValue
import de.jensklingenberg.scratch.common.OpCode
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import java.util.UUID


private data class Say(private val content: ReporterBlock, private val seconds: Int? = null) : Node, StackBlock {

    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context,

        ) {

        val operatorUUID = UUID.randomUUID().toString()

        val inputMap = mutableMapOf(
            "MESSAGE" to when (content) {
                is StringBlock -> createMessage(1, ScratchType.STRING.value, content.value)
                is ScratchVariable -> {
                    val id = context.variableMap[content.name].toString()
                        ?: throw IllegalArgumentException("Variable not found")
                    setValue(content, id, context)
                }

                is ScratchList -> setValue(content, operatorUUID, context)

                else -> {
                    JsonArray(
                        listOf(
                            JsonPrimitive(ScratchType.BLOCKREF.value),
                            JsonPrimitive(operatorUUID.toString()),
                            JsonArray(
                                listOf(
                                    JsonPrimitive(ScratchType.STRING.value),
                                    JsonPrimitive("Hello")
                                )
                            )
                        )
                    )
                }
            }
        )

        if (seconds != null) {
            inputMap["SECS"] = JsonArray(
                listOf(
                    JsonPrimitive(1),
                    JsonArray(
                        listOf(
                            JsonPrimitive(ScratchType.POSITIVE_NUMBER.value),
                            JsonPrimitive(seconds.toString())
                        )
                    )
                )
            )
        }

        val opCode = when (seconds) {
            null -> OpCode.LooksSay
            else -> OpCode.looks_sayforsecs
        }
        val spec = BlockSpec(
            opcode = opCode,
            inputs = inputMap
        )
        visitors[identifier] = spec.toBlock(nextUUID, parent)

        content.visit(
            visitors,
            identifier,
            operatorUUID,
            null,
            context
        )

    }


}

sealed interface LooksSayContent {
    class Literal(val message: String) : LooksSayContent
    class Reporter(val operatorSpec: ReporterBlock) : LooksSayContent
}


fun ScriptBuilder.say(reporterBlock: ReporterBlock) = addNode(Say(reporterBlock))
fun ScriptBuilder.say(message: Double, seconds: Int? = null) = say(message.toString(), seconds)
fun ScriptBuilder.say(message: String, seconds: Int? = null) = addNode(Say(StringBlock(message), seconds))