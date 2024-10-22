package me.jens.scratch.sensing


import me.jens.scratch.common.Block
import me.jens.scratch.common.BlockSpec
import me.jens.scratch.common.Context
import me.jens.scratch.common.Node
import me.jens.scratch.common.OpCode
import me.jens.scratch.common.createBlockRef
import me.jens.scratch.common.createLiteralMessage
import me.jens.scratch.looks.LooksSayContent
import java.util.UUID

data class AskandWait(val content: LooksSayContent): Node {
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
        visitors[identifier.toString()] = spec.toBlock(nextUUID?.toString(), parent, layer == 0 && index == 0)

        if (content is LooksSayContent.Reporter) {
            content.operatorSpec.visit(visitors, identifier.toString(), index + 1, operatorUUID, null, layer + 1, context)
        }
    }
}