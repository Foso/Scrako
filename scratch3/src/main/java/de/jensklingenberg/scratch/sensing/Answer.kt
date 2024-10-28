package de.jensklingenberg.scratch.sensing

import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scratch.common.OpCode

object Answer : BlockSpec(
    opcode = OpCode.sensing_answer,
), ReporterBlock

