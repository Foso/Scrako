package me.jens.scratch.motion

import me.jens.scratch.common.BlockSpec
import me.jens.scratch.common.OpCode
import me.jens.scratch.common.ReporterBlock

class Direction : BlockSpec(
    opcode = OpCode.motion_direction,
), ReporterBlock