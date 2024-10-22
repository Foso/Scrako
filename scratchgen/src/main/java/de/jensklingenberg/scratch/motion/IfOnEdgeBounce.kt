package de.jensklingenberg.scratch.motion

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode

class IfOnEdgeBounce : BlockSpec(
    opcode = OpCode.motion_ifonedgebounce
)

fun NodeBuilder.ifOnEdgeBounce() = addChild(IfOnEdgeBounce())