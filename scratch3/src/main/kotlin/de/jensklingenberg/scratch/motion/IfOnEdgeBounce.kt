package de.jensklingenberg.scratch.motion

import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec

private class IfOnEdgeBounce : BlockSpec(
    opcode = "motion_ifonedgebounce"
)

fun ScriptBuilder.ifOnEdgeBounce() = addNode(IfOnEdgeBounce())