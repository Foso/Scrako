package de.jensklingenberg.scratch.looks

import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scratch.common.OpCode

private class NextCostume : BlockSpec(
    opcode = OpCode.looks_nextcostume,
)

fun ScriptBuilder.nextCostume() = addChild(NextCostume())
