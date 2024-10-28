package de.jensklingenberg.scratch.looks

import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scratch.common.OpCode

object Size : BlockSpec(
    opcode = OpCode.looks_size,
), ReporterBlock