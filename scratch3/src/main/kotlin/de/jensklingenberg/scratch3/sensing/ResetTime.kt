package de.jensklingenberg.scratch3.sensing

import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scratch3.common.OpCode

private class ResetTime : BlockSpec(
    opcode = OpCode.sensing_resettimer,
)

fun ScriptBuilder.resetTime() = addNode(ResetTime())