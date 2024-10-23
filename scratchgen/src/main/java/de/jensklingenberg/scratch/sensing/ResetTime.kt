package de.jensklingenberg.scratch.sensing

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode

class ResetTime : BlockSpec(
    opcode = OpCode.sensing_resettimer,
)

fun NodeBuilder.resetTime() = addChild(ResetTime())