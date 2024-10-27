package de.jensklingenberg.scratch.sensing

import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scrako.common.ReporterBlock

object Answer : BlockSpec(
    opcode = OpCode.sensing_answer,
), ReporterBlock

