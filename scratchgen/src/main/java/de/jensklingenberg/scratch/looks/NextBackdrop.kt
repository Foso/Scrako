package de.jensklingenberg.scratch.looks

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode

private class NextBackdrop : BlockSpec(
    opcode = OpCode.looks_nextbackdrop,
)

fun NodeBuilder.nextBackdrop() = addChild(NextBackdrop())