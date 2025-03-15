package de.jensklingenberg.scratch3.motion

import de.jensklingenberg.scrako.builder.SpriteScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec

private class IfOnEdgeBounce : BlockSpec(
    opcode = "motion_ifonedgebounce"
)

fun SpriteScriptBuilder.ifOnEdgeBounce() = addNode(IfOnEdgeBounce())