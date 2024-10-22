package me.jens.scratch.sensing

import me.jens.scratch.common.BlockSpec
import me.jens.scratch.common.OpCode
import me.jens.scratch.common.ReporterBlock

class DaysSince2000() : BlockSpec(
    opcode = OpCode.sensing_dayssince2000,
), ReporterBlock