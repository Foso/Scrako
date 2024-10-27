package de.jensklingenberg.scratch.sensing

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.ScriptBuilder
import de.jensklingenberg.scratch.common.OpCode

private class ResetTime : BlockSpec(
    opcode = OpCode.sensing_resettimer,
)

fun ScriptBuilder.resetTime() = addChild(ResetTime())