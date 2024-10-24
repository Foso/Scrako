package de.jensklingenberg.scratch.looks

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode

private class NextCostume : BlockSpec(
    opcode = OpCode.looks_nextcostume,
)

fun NodeBuilder.nextCostume() = addChild(NextCostume())
