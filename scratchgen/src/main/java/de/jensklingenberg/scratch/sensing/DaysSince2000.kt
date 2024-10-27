package de.jensklingenberg.scratch.sensing

import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scratch.common.OpCode

object DaysSince2000 : BlockSpec(
    opcode = OpCode.sensing_dayssince2000,
), ReporterBlock