package de.jensklingenberg.scratch3.looks

import de.jensklingenberg.scrako.builder.SpriteScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec

private class Show : BlockSpec(
    opcode = "looks_show",
)

fun SpriteScriptBuilder.show() = addNode(Show())