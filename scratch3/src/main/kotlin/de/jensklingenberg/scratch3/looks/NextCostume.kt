package de.jensklingenberg.scratch3.looks

import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec

private class NextCostume : BlockSpec(
    opcode = "looks_nextcostume",
)

fun CommonScriptBuilder.nextCostume() = addNode(NextCostume())
