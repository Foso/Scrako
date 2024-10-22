package de.jensklingenberg.scratch.motion

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.operator.createNum

class TurnRight(steps: Int) : BlockSpec(
    opcode = OpCode.motion_turnright,
    inputs = mapOf("DEGREES" to createNum(steps.toString()))
)

fun NodeBuilder.turnRight(steps: Int) = addChild(TurnRight(steps))