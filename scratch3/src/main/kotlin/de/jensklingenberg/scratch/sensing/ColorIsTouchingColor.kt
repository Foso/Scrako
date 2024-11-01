package de.jensklingenberg.scratch.sensing

import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.BooleanBlock
import de.jensklingenberg.scrako.common.ScratchType
import de.jensklingenberg.scrako.common.createMessage

private class ColorIsTouchingColor(color: String, color2: String) : BlockSpec(
    opcode = "sensing_coloristouchingcolor",
    inputs = mapOf(
        "COLOR" to createMessage(1, ScratchType.COLOR.value, color),
        "COLOR2" to createMessage(2, ScratchType.COLOR.value, color2)
    )
), BooleanBlock


fun colorIsTouchingColor(color: String, color2: String): BooleanBlock = ColorIsTouchingColor(color, color2)