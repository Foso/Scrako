package de.jensklingenberg.scratch3.looks

import de.jensklingenberg.scrako.builder.SpriteScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec

private class NextCostume : BlockSpec(
    opcode = "looks_nextcostume",
)

fun SpriteScriptBuilder.nextCostume() = addNode(NextCostume())
