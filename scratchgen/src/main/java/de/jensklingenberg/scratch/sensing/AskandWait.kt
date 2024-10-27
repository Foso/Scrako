package de.jensklingenberg.scratch.sensing


import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.Context
import de.jensklingenberg.scratch.common.Node
import de.jensklingenberg.scratch.common.ScriptBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.createBlockRef
import de.jensklingenberg.scratch.common.createLiteralMessage
import de.jensklingenberg.scratch.looks.LooksSayContent
import de.jensklingenberg.scratch.model.Block
import java.util.UUID

private data class AskandWait(val content: LooksSayContent) : Node {
    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        identifier: UUID,
        nextUUID: UUID?,
        context: Context
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
                null,
                context.copy(topLevel = false)
            )
        }
    }
}

fun ScriptBuilder.askAndWait(content: LooksSayContent) = addChild(AskandWait(content))