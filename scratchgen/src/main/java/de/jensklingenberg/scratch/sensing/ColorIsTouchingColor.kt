package de.jensklingenberg.scratch.sensing

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.NodeBuilder
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.common.createMessage
import de.jensklingenberg.scratch.operator.BooleanBlock

class ColorIsTouchingColor(color: String, color2: String) : BlockSpec(
    opcode = OpCode.sensing_coloristouchingcolor,
    inputs = mapOf(
        "COLOR" to createMessage(1, 9, color),
        "COLOR2" to createMessage(2, 9, color2)
    )
), BooleanBlock

fun NodeBuilder.colorIsTouchingColor(color: String, color2: String) = addChild(ColorIsTouchingColor(color, color2))