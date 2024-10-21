package me.jens.scratch.looks

import me.jens.OperatorSpec
import me.jens.createBlockRef
import me.jens.createLiteralMessage
import me.jens.createSecs
import me.jens.scratch.BlockSpecSpec
import me.jens.scratch.common.Node
import me.jens.scratch.common.OpCode
import scratch.Block
import java.util.UUID

data class Say(private val content: LooksSayContent, private val seconds: Int? = null) : Node {

    override fun visit(
        visitors: MutableMap<String, Block>,
        parent: String?,
        index: Int,
        name: UUID,
        nextUUID: UUID?,
        layer: Int
    ) {
        val name2 = name.toString()
        val newNext = nextUUID?.toString()

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
            null -> OpCode.LooksSay
            else -> OpCode.looks_sayforsecs
        }
        val spec = BlockSpecSpec(
            opcode = opCode,
            inputs = inputMap
        )
        visitors[name2] = spec.toBlock(newNext, parent, layer == 0 && index == 0)

        if (content is LooksSayContent.Operators) {
            content.operatorSpec.visit(visitors, name2, index + 1, operatorUUID, null)
        }
    }
}

sealed interface LooksSayContent {
    class Literal(val message: String) : LooksSayContent
    class Operators(val operatorSpec: OperatorSpec) : LooksSayContent
    class Keywords(val keyword: ScratchKeywords) : LooksSayContent
}


fun Say(message: String, seconds: Int? = null) = Say(LooksSayContent.Literal(message), seconds)