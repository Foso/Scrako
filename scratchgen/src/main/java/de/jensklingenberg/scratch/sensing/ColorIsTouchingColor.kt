package de.jensklingenberg.scratch.sensing

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock
import de.jensklingenberg.scratch.common.ScratchType
import de.jensklingenberg.scratch.common.createMessage
import de.jensklingenberg.scratch.event.Key
import de.jensklingenberg.scratch.operator.BooleanBlock

private class ColorIsTouchingColor(color: String, color2: String) : BlockSpec(
    opcode = OpCode.sensing_coloristouchingcolor,
    inputs = mapOf(
        "COLOR" to createMessage(1, ScratchType.COLOR.value, color),
        "COLOR2" to createMessage(2, ScratchType.COLOR.value, color2)
    )
), BooleanBlock

class KeyReporter(val key: Key) : ReporterBlock


fun colorIsTouchingColor(color: String, color2: String): BooleanBlock = ColorIsTouchingColor(color, color2)