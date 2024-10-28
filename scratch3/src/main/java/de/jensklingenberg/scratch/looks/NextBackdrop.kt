package de.jensklingenberg.scratch.looks

import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scratch.common.OpCode

private class NextBackdrop : BlockSpec(
    opcode = OpCode.looks_nextbackdrop,
)

fun ScriptBuilder.nextBackdrop() = addChild(NextBackdrop())