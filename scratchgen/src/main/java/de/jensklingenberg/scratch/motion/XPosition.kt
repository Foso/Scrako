package de.jensklingenberg.scratch.motion

import de.jensklingenberg.scrako.common.BlockSpec
import de.jensklingenberg.scrako.common.ReporterBlock
import de.jensklingenberg.scratch.common.OpCode

object XPosition : BlockSpec(
    opcode = OpCode.motion_xposition,
), ReporterBlock