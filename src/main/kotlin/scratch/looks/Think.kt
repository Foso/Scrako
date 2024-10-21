package me.jens.scratch.looks

import me.jens.createBlockRef
import me.jens.createLiteralMessage
import me.jens.createSecs
import me.jens.scratch.Block
import me.jens.scratch.BlockSpecSpec
import me.jens.scratch.common.Context
import me.jens.scratch.common.Node
import me.jens.scratch.common.OpCode
import me.jens.scratch.common.ReporterBlock
import java.util.UUID

data class Think(private val content: LooksSayContent, private val seconds: Int? = null) : Node {

    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        index: Int,
        name: UUID,
        nextUUID: UUID?,
        layer: Int,
        context: Context
    ) {

        val operatorUUID = UUID.randomUUID()

        val inputMap = mutableMapOf(
            "MESSAGE" to when (content) {
                is LooksSayContent.Literal -> createLiteralMessage(content.message)
                is LooksSayContent.Operators -> {
                    createBlockRef(operatorUUID.toString())
                }

                is LooksSayContent.Keywords -> {
                    createBlockRef(content.keyword.name.toLowerCase())
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
        val spec = BlockSpecSpec(
            opcode = opCode,
            inputs = inputMap
        )
        visitors[name.toString()] = spec.toBlock(nextUUID?.toString(), parent, layer == 0 && index == 0)

        if (content is LooksSayContent.Operators) {
            content.operatorSpec.visit(visitors, name.toString(), index + 1, operatorUUID, null, layer + 1, context)
        }
    }
}


fun Think(message: String, seconds: Int? = null) = Think(LooksSayContent.Literal(message), seconds)