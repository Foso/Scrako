package de.jensklingenberg.scratch3.sensing

import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.BooleanBlock
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scratch3.common.OpCode

object Timer : BlockSpec(
    opcode = "sensing_timer",
), ReporterBlock

object Loudness : BlockSpec(
    opcode = "sensing_loudness",
), ReporterBlock

object Username : BlockSpec(
    opcode = "sensing_username",
), ReporterBlock

object MouseDown : BlockSpec(
    opcode = "sensing_mousedown",
), BooleanBlock

object MouseX : BlockSpec(
    opcode = "sensing_mousex",
), ReporterBlock

object MouseY : BlockSpec(
    opcode = "sensing_mousey",
), ReporterBlock

object Volume : BlockSpec(
    opcode = OpCode.sound_volume,
), ReporterBlock