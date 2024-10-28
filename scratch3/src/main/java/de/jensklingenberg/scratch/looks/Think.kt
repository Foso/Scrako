package de.jensklingenberg.scratch.looks


import de.jensklingenberg.scrako.common.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ScratchType
import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.common.createMessage
import de.jensklingenberg.scrako.common.getScratchType
import de.jensklingenberg.scratch.common.OpCode
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import java.util.UUID

private data class Think(private val content: LooksSayContent, private val seconds: Int? = null) : Node {

    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context,

        ) {

        val operatorUUID = UUID.randomUUID()

        val inputMap = mutableMapOf(
            "MESSAGE" to when (content) {
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

        if (seconds != null) {
            inputMap["SECS"] = getScratchType(seconds.toString(), ScratchType.NUMBER)
        }

        val opCode = when (seconds) {
            null -> OpCode.LooksThink
            else -> OpCode.looks_thinkforsecs
        }
        val spec = BlockSpec(
            opcode = opCode,
            inputs = inputMap
        )
        visitors[identifier.toString()] = spec.toBlock(nextUUID, parent)

        if (content is LooksSayContent.Reporter) {
            content.operatorSpec.visit(
                visitors,
                identifier.toString(),
                operatorUUID,
                null, context,

                )
        }
    }
}

fun ScriptBuilder.think(message: String, seconds: Int? = null) =
    addNode(Think(LooksSayContent.Literal(message), seconds))

