package me.jens.scratch.motion

import me.jens.scratch.common.BlockSpec
import me.jens.scratch.common.OpCode
import me.jens.scratch.operator.createNum

class Move(steps: Int) : BlockSpec(
    opcode = OpCode.motion_movesteps,
    inputs = mapOf("STEPS" to createNum(steps.toString()))
)

