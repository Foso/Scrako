package de.jensklingenberg.scratch.looks

import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scratch.common.OpCode

private class Show : BlockSpec(
    opcode = OpCode.looks_show,
)

fun ScriptBuilder.show() = addNode(Show())