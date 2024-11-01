package de.jensklingenberg.scratch.looks

import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scratch.common.OpCode

private class Hide : BlockSpec(
    opcode = OpCode.looks_hide,
)

fun ScriptBuilder.hide() = addNode(Hide())



