package de.jensklingenberg.scratch3.sensing

import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec

private class ResetTime : BlockSpec(
    opcode = "sensing_resettimer",
)

fun CommonScriptBuilder.resetTime() = addNode(ResetTime())