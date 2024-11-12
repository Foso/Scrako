package de.jensklingenberg.scratch3.looks

import de.jensklingenberg.scrako.builder.SpriteScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec

private class Hide : BlockSpec(
    opcode = "looks_hide",
)

fun SpriteScriptBuilder.hide() = addNode(Hide())



//looks_goforwardbackwardlayers

