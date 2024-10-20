package me.jens.scratch.sensing

import me.jens.createLiteralMessage
import me.jens.scratch.BlockSpecSpec
import me.jens.scratch.OpCode

fun SensingAnswerAndWait(question: String) = BlockSpecSpec(
    opcode = OpCode.sensing_askandwait,
    inputs = mapOf(
        "QUESTION" to createLiteralMessage(question)
    )
)