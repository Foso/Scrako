package de.jensklingenberg.scratch3.looks

import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scratch3.common.OpCode

private class NextBackdrop : BlockSpec(
    opcode = OpCode.looks_nextbackdrop,
)

fun ScriptBuilder.nextBackdrop() = addNode(NextBackdrop())