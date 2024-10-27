package de.jensklingenberg.scratch.looks

import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scrako.common.ReporterBlock

object Size : BlockSpec(
    opcode = OpCode.looks_size,
), ReporterBlock