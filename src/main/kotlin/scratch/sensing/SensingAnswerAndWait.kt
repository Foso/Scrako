package me.jens.scratch.sensing

import me.jens.scratch.common.BlockSpec
import me.jens.scratch.common.OpCode
import me.jens.scratch.common.createLiteralMessage

fun SensingAnswerAndWait(question: String) = BlockSpec(
    opcode = OpCode.sensing_askandwait,
    inputs = mapOf(
        "QUESTION" to createLiteralMessage(question)
    )
)