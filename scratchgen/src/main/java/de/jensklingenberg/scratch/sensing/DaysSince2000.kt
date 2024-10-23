package de.jensklingenberg.scratch.sensing

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock

object DaysSince2000 : BlockSpec(
    opcode = OpCode.sensing_dayssince2000,
), ReporterBlock