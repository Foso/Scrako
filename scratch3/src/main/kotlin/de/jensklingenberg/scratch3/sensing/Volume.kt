package de.jensklingenberg.scratch3.sensing

import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scratch3.common.OpCode

object Volume : BlockSpec(
    opcode = OpCode.sound_volume,
), ReporterBlock