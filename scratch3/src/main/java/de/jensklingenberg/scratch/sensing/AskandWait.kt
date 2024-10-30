package de.jensklingenberg.scratch.sensing


import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.model.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ScratchType
import de.jensklingenberg.scrako.common.createMessage
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.looks.LooksSayContent
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import java.util.UUID

private data class AskandWait(val content: LooksSayContent) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: String,
        nextUUID: String?,
        context: Context,

        ) {
        val operatorUUID = UUID.randomUUID().toString()

        val inputMap = mutableMapOf(
            "QUESTION" to when (content) {
                is LooksSayContent.Literal -> createMessage(1, ScratchType.STRING.value, content.message)
                is LooksSayContent.Reporter -> {
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

        val spec = BlockSpec(
            opcode = OpCode.sensing_askandwait,
            inputs = inputMap
        )
        visitors[identifier] = spec.toBlock(nextUUID, parent)

        if (content is LooksSayContent.Reporter) {
            content.operatorSpec.visit(
                visitors,
                identifier,
                operatorUUID,
                null, context,

                )
        }
    }
}

fun ScriptBuilder.askAndWait(content: LooksSayContent) = addNode(AskandWait(content))