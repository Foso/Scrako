package de.jensklingenberg.scratch3.sensing

import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.BooleanBlock
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scratch3.common.OpCode

object Timer : BlockSpec(
    opcode = OpCode.sensing_timer,
), ReporterBlock

object Loudness : BlockSpec(
    opcode = OpCode.sensing_loudness,
), ReporterBlock

object Username : BlockSpec(
    opcode = OpCode.sensing_username,
), ReporterBlock

object MouseDown : BlockSpec(
    opcode = OpCode.sensing_mousedown,
), BooleanBlock

object MouseX : BlockSpec(
    opcode = OpCode.sensing_mousex,
), ReporterBlock

object MouseY : BlockSpec(
    opcode = OpCode.sensing_mousey,
), ReporterBlock

object Volume : BlockSpec(
    opcode = OpCode.sound_volume,
), ReporterBlock