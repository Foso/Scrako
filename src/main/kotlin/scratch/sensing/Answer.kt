package me.jens.scratch.sensing

import me.jens.scratch.common.BlockSpec
import me.jens.scratch.common.OpCode
import me.jens.scratch.common.ReporterBlock

class Answer() : BlockSpec(
    opcode = OpCode.sensing_answer,
), ReporterBlock

