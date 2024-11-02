package de.jensklingenberg.scratch3.looks

import de.jensklingenberg.scrako.builder.ScriptBuilder
import de.jensklingenberg.scrako.common.BlockSpec

private class NextBackdrop : BlockSpec(
    opcode = "looks_nextbackdrop",
)

fun ScriptBuilder.nextBackdrop() = addNode(NextBackdrop())