package de.jensklingenberg.scratch3.sensing

import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scratch3.common.OpCode

object DaysSince2000 : BlockSpec(
    opcode = OpCode.sensing_dayssince2000,
), ReporterBlock