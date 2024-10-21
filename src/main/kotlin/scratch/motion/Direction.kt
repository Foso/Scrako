package me.jens.scratch.motion

import me.jens.scratch.BlockSpecSpec
import me.jens.scratch.common.OpCode
import me.jens.scratch.common.ReporterBlock

class Direction() : BlockSpecSpec(
    opcode = OpCode.motion_direction,
), ReporterBlock

