package de.jensklingenberg.scratch3.looks

import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec

private class Show : BlockSpec(
    opcode = "looks_show",
)

fun CommonScriptBuilder.show() = addNode(Show())