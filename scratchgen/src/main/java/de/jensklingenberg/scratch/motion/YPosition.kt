package de.jensklingenberg.scratch.motion

import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scratch.common.OpCode
import de.jensklingenberg.scrako.common.ReporterBlock

object YPosition : BlockSpec(
    opcode = OpCode.motion_yposition,
), ReporterBlock