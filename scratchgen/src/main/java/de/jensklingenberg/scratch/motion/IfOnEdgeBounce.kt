package de.jensklingenberg.scratch.motion

import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.ScriptBuilder
import de.jensklingenberg.scratch.common.OpCode

private class IfOnEdgeBounce : BlockSpec(
    opcode = OpCode.motion_ifonedgebounce
)

fun ScriptBuilder.ifOnEdgeBounce() = addChild(IfOnEdgeBounce())