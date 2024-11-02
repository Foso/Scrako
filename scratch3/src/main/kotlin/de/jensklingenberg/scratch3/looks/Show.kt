package de.jensklingenberg.scratch3.looks

import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scratch3.common.OpCode

private class Show : BlockSpec(
    opcode = OpCode.looks_show,
)

fun CommonScriptBuilder.show() = addNode(Show())