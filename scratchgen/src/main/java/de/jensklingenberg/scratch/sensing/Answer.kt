package de.jensklingenberg.scratch.sensing

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock

class Answer() : BlockSpec(
    opcode = OpCode.sensing_answer,
), ReporterBlock

