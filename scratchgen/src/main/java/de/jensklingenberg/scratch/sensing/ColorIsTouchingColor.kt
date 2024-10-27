package de.jensklingenberg.scratch.sensing

import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.BooleanBlock
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scrako.common.ScratchType
import de.jensklingenberg.scrako.common.createMessage
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.event.Key

private class ColorIsTouchingColor(color: String, color2: String) : BlockSpec(
    opcode = OpCode.sensing_coloristouchingcolor,
    inputs = mapOf(
        "COLOR" to createMessage(1, ScratchType.COLOR.value, color),
        "COLOR2" to createMessage(2, ScratchType.COLOR.value, color2)
    )
), BooleanBlock

class KeyReporter(val key: Key) : ReporterBlock


fun colorIsTouchingColor(color: String, color2: String): BooleanBlock = ColorIsTouchingColor(color, color2)