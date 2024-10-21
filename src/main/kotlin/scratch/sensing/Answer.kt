package me.jens.scratch.sensing

import me.jens.scratch.BlockSpecSpec
import me.jens.scratch.common.OpCode
import me.jens.scratch.common.ReporterBlock

class Answer() : BlockSpecSpec(
    opcode = OpCode.sensing_answer,
), ReporterBlock