package me.jens.scratch.motion

import me.jens.scratch.common.BlockSpec
import me.jens.scratch.common.OpCode
import me.jens.scratch.common.ReporterBlock

class XPosition() : BlockSpec(
    opcode = OpCode.motion_xposition,
), ReporterBlock