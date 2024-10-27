package de.jensklingenberg.scratch.looks

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.ScriptBuilder
import de.jensklingenberg.scratch.common.OpCode

private class NextCostume : BlockSpec(
    opcode = OpCode.looks_nextcostume,
)

fun ScriptBuilder.nextCostume() = addChild(NextCostume())
