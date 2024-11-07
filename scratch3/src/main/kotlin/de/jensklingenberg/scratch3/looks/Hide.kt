package de.jensklingenberg.scratch3.looks

import de.jensklingenberg.scrako.builder.CommonScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.ReporterBlock

private class Hide : BlockSpec(
    opcode = "looks_hide",
)

fun CommonScriptBuilder.hide() = addNode(Hide())



//looks_goforwardbackwardlayers

