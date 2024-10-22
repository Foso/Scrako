package de.jensklingenberg.scratch.sensing

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.createLiteralMessage

fun SensingAnswerAndWait(question: String) = BlockSpec(
    opcode = OpCode.sensing_askandwait,
    inputs = mapOf(
        "QUESTION" to createLiteralMessage(question)
    )
)