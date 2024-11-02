package de.jensklingenberg.scratch3.looks

import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scratch3.common.OpCode

private class NextCostume : BlockSpec(
    opcode = OpCode.looks_nextcostume,
)

fun CommonScriptBuilder.nextCostume() = addNode(NextCostume())
