package me.jens.scratch.looks

import me.jens.OperatorSpec
import me.jens.createBlockRef
import me.jens.createLiteralMessage
import me.jens.createSecs
import me.jens.scratch.BlockSpecSpec
import scratch.Block
import me.jens.scratch.OpCode
import scratch.Visitor

data class Say(private val content: LooksSayContent, private val seconds: Int? = null) : Visitor {

    override fun visit(visitors: MutableMap<String, Block>, layer: Int, parent: String?, index: Int, next: String?) {
        val name = "block$index$layer"

        val inputMap = mutableMapOf(
            "MESSAGE" to when (content) {
                is LooksSayContent.Literal -> createLiteralMessage(content.message)
                is LooksSayContent.Operators -> {
                    createBlockRef("block0${layer + 1}")
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
        visitors[name] = spec.toBlock(next, parent, layer == 0 && index == 0)

        if (content is LooksSayContent.Operators) {
            content.operatorSpec.visit(visitors, layer + 1, name, 0, next)
        }
    }
}

sealed interface LooksSayContent {
    class Literal(val message: String) : LooksSayContent
    class Operators(val operatorSpec: OperatorSpec) : LooksSayContent
    class Keywords(val keyword: ScratchKeywords) : LooksSayContent
}


fun Say( message: String, seconds: Int? = null) = Say(LooksSayContent.Literal(message), seconds)