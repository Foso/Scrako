package de.jensklingenberg.scratch.motion

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.operator.createNum

private class PointInDirection(degrees: Int) : BlockSpec(
    opcode = OpCode.motion_pointindirection,
    inputs = mapOf("DIRECTION" to createNum(degrees.toString()))
)

fun NodeBuilder.pointInDirection(degrees: Int) = addChild(PointInDirection(degrees))