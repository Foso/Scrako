package me.jens.scratch.motion

import me.jens.createNum
import me.jens.scratch.BlockSpecSpec
import me.jens.scratch.OpCode

class MoveSteps(steps: Int) : BlockSpecSpec(
   opcode = OpCode.motion_movesteps,
   inputs = mapOf("STEPS" to createNum(steps.toString()))
)

