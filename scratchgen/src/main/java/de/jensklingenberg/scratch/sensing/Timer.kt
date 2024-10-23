package de.jensklingenberg.scratch.sensing

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock

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
), ReporterBlock

object MouseX : BlockSpec(
    opcode = OpCode.sensing_mousex,
), ReporterBlock

object MouseY : BlockSpec(
    opcode = OpCode.sensing_mousey,
), ReporterBlock