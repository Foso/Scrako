package de.jensklingenberg.scratch3.motion

import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec

private class IfOnEdgeBounce : BlockSpec(
    opcode = "motion_ifonedgebounce"
)

fun CommonScriptBuilder.ifOnEdgeBounce() = addNode(IfOnEdgeBounce())