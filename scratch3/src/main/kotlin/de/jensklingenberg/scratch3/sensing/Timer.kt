package de.jensklingenberg.scratch3.sensing

import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.BooleanBlock
import de.jensklingenberg.scrako.common.ReporterBlock

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
    opcode = "sound_volume",
), ReporterBlock

object Answer : BlockSpec(
    opcode = "sensing_answer",
), ReporterBlock

object DaysSince2000 : BlockSpec(
    opcode = "sensing_dayssince2000",
), ReporterBlock