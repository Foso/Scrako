package de.jensklingenberg.scratch.motion

import de.jensklingenberg.scratch.common.BlockSpec
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scratch.common.ReporterBlock

object YPosition : BlockSpec(
    opcode = OpCode.motion_yposition,
), ReporterBlock