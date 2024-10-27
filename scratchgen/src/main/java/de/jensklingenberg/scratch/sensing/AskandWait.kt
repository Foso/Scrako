package de.jensklingenberg.scratch.sensing


import de.jensklingenberg.scrako.common.Block
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.Context
import de.jensklingenberg.scrako.common.Node
import de.jensklingenberg.scrako.common.ScriptBuilder
import de.jensklingenberg.scrako.common.createBlockRef
import de.jensklingenberg.scrako.common.createLiteralMessage
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.looks.LooksSayContent
import java.util.UUID

private data class AskandWait(val content: LooksSayContent) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context,

        ) {
        val operatorUUID = UUID.randomUUID()

        val inputMap = mutableMapOf(
            "QUESTION" to when (content) {
                is LooksSayContent.Literal -> createLiteralMessage(content.message)
                is LooksSayContent.Reporter -> {
                    createBlockRef(operatorUUID.toString())
                }
            }
        )

        val spec = BlockSpec(
            opcode = OpCode.sensing_askandwait,
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

fun ScriptBuilder.askAndWait(content: LooksSayContent) = addChild(AskandWait(content))