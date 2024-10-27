package de.jensklingenberg.scratch.looks


import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ScriptBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scrako.common.ScratchType
import de.jensklingenberg.scrako.common.createBlockRef
import de.jensklingenberg.scrako.common.createLiteralMessage
import de.jensklingenberg.scrako.common.getScratchType
import de.jensklingenberg.scrako.common.Block
import java.util.UUID

private data class Think(private val content: LooksSayContent, private val seconds: Int? = null) : Node {

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
                is LooksSayContent.Literal -> createLiteralMessage(content.message)
                is LooksSayContent.Reporter -> {
                    createBlockRef(operatorUUID.toString())
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
                null,
                context
            )
        }
    }
}

fun ScriptBuilder.think(message: String, seconds: Int? = null) =
    addChild(Think(LooksSayContent.Literal(message), seconds))

