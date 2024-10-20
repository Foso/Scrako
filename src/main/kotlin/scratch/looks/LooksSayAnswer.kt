package me.jens.scratch.looks

import me.jens.createBlockRef
import me.jens.scratch.BlockSpecSpec
import me.jens.scratch.OpCode

fun LooksSayAnswer() = BlockSpecSpec(
    opcode = OpCode.LooksSay,
    inputs = mapOf(
        "MESSAGE" to createBlockRef("answer")
    )
)