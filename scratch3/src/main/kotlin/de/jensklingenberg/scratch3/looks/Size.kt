package de.jensklingenberg.scratch3.looks

import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scratch3.common.OpCode

object Size : BlockSpec(
    opcode = OpCode.looks_size,
), ReporterBlock