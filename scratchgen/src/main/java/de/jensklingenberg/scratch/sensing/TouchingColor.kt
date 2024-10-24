package de.jensklingenberg.scratch.sensing

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.common.createMessage
import de.jensklingenberg.scratch.operator.BooleanBlock

private class TouchingColor(color: String) : BlockSpec(
    opcode = OpCode.sensing_touchingcolor,
    inputs = mapOf(
        "COLOR" to createMessage(1, 9, color)
    )
), BooleanBlock

fun NodeBuilder.touchingColor(color: String) = addChild(TouchingColor(color))

