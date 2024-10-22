package me.jens.scratch.sensing

import me.jens.scratch.common.BlockSpec
import me.jens.scratch.common.OpCode
import me.jens.scratch.common.ReporterBlock

class Volume() : BlockSpec(
    opcode = OpCode.sound_volume,
), ReporterBlock