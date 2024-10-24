package de.jensklingenberg.scratch.motion

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.operator.createNum

private class Move(steps: Double) : BlockSpec(
    opcode = OpCode.motion_movesteps,
    inputs = mapOf("STEPS" to createNum(steps.toString()))
)

fun NodeBuilder.move(steps: Double) = addChild(Move(steps))