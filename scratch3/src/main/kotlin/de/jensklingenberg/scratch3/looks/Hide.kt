package de.jensklingenberg.scratch3.looks

import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec

private class Hide : BlockSpec(
    opcode = "looks_hide",
)

fun ScriptBuilder.hide() = addNode(Hide())



