package de.jensklingenberg.scratch3.sensing

import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec

private class ResetTime : BlockSpec(
    opcode = "sensing_resettimer",
)

fun ScriptBuilder.resetTime() = addNode(ResetTime())