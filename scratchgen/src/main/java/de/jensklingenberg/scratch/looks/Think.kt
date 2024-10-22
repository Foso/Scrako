package de.jensklingenberg.scratch.looks


import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.createBlockRef
import de.jensklingenberg.scratch.common.createLiteralMessage
import de.jensklingenberg.scratch.common.createSecs
import de.jensklingenberg.scratch.model.Block
import java.util.UUID

data class Think(private val content: LooksSayContent, private val seconds: Int? = null) : Node {

    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        index: Int,
        identifier: UUID,
        nextUUID: UUID?,
        layer: Int,
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
            inputMap["SECS"] = createSecs(seconds.toString())
        }

        val opCode = when (seconds) {
            null -> OpCode.LooksThink
            else -> OpCode.looks_thinkforsecs
        }
        val spec = BlockSpec(
            opcode = opCode,
            inputs = inputMap
        )
        visitors[identifier.toString()] = spec.toBlock(nextUUID?.toString(), parent, layer == 0 && index == 0)

        if (content is LooksSayContent.Reporter) {
            content.operatorSpec.visit(
                visitors,
                identifier.toString(),
                index + 1,
                operatorUUID,
                null,
                layer + 1,
                context
            )
        }
    }
}


fun Think(message: String, seconds: Int? = null) = Think(LooksSayContent.Literal(message), seconds)