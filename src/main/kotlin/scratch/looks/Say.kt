package me.jens.scratch.looks


import me.jens.scratch.common.Block
import me.jens.scratch.common.BlockSpec
import me.jens.scratch.common.Context
import me.jens.scratch.common.Node
import me.jens.scratch.common.OpCode
import me.jens.scratch.common.ReporterBlock
import me.jens.scratch.common.createBlockRef
import me.jens.scratch.common.createLiteralMessage
import me.jens.scratch.common.createSecs
import java.util.UUID

fun Say(reporterBlock: ReporterBlock) = Say(LooksSayContent.Reporter(reporterBlock))

data class Say(private val content: LooksSayContent, private val seconds: Int? = null) : Node {

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
            null -> OpCode.LooksSay
            else -> OpCode.looks_sayforsecs
        }
        val spec = BlockSpec(
            opcode = opCode,
            inputs = inputMap
        )
        visitors[identifier.toString()] = spec.toBlock(nextUUID?.toString(), parent, layer == 0 && index == 0)

        if (content is LooksSayContent.Reporter) {
            content.operatorSpec.visit(visitors, identifier.toString(), index + 1, operatorUUID, null, layer + 1, context)
        }
    }
}

sealed interface LooksSayContent {
    class Literal(val message: String) : LooksSayContent
    class Reporter(val operatorSpec: ReporterBlock) : LooksSayContent
}


fun Say(message: String, seconds: Int? = null) = Say(LooksSayContent.Literal(message), seconds)